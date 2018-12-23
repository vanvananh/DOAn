package com.ptit.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ptit.entity.Comment;
import com.ptit.entity.Staff;
import com.ptit.mining.Cmd;
import com.ptit.mining.UtilMining;
import com.ptit.mining.VnToken;
import com.ptit.repository.CommentRepository;
import com.ptit.util.Constants;
import com.ptit.util.MethodUtil;
import com.ptit.util.Paging;
import com.ptit.util.Sorting;
import com.ptit.util.Constants.Operation;
import com.ptit.util.filter.FilterStaff;
import com.ptit.util.specification.CriteriaCustom;
import com.ptit.util.specification.SpecificationBuilder;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepository commentRepository;

	@Override
	public Comment createComment(Comment cmt) throws IOException {
		// TODO Auto-generated method stub
		Cmd.runCmd("svm_classify data/test.dat data/model data/predictions");
		boolean isNega = VnToken.isNagative(cmt.getContent());
		cmt.setIsNegative(isNega);
		cmt = commentRepository.save(cmt);
		return commentRepository.findOne(cmt.getCommentId());
	}
	
	@Override
	public Page<Comment> getAllComment(Paging paging, Sorting sorting, String keywordSearch, FilterStaff filter) {
		SpecificationBuilder<Comment> specification = new SpecificationBuilder<Comment>();
	    // search
	    if (!MethodUtil.isNull(keywordSearch)) {
	      specification.addCriteriaCustom(new CriteriaCustom(Constants.NameColume.CONTENT_SEARCH,
	          Constants.Operation.LIKE, keywordSearch));
	    }
	    
	      // roleId
//	      if (!MethodUtil.isNull(filter.getStaffRoleId())) {
//	        specification
//	            .addCriteriaCustom(new CriteriaCustom(Constants.NameColume.STAFF_FILTER_ROLE_ID,
//	                Operation.EQUAL, filter.getStaffRoleId()));
//	      }
//	    
	    
	    sorting.and(Direction.DESC, Constants.NameColume.CREATE_DATE);
	    
	    // query with paging
	    if (!MethodUtil.isNull(paging)) {
	      return commentRepository.findAll(specification.build(), MethodUtil.Pagination(paging, sorting));
	    }
	    // else query not paging
	    return new PageImpl<Comment>(
	    		commentRepository.findAll(specification.build(), MethodUtil.convertSort(sorting)));
			
	}

	@Override
	public void training(List<Integer> ids) throws IOException {
		for(Integer i : ids) {
			Comment c = commentRepository.findOne(i);
			String s[] = VnToken.tokentizer(c.getContent());
			VnToken.writeFileTrain(s, c.getIsNegative());
			c.setTraining(true);
			commentRepository.save(c);
		}
		
		
	}

	@Override
	public Comment getById(int id) {
		// TODO Auto-generated method stub
		return commentRepository.findOne(id);
	}

	@Override
	public void updateIsNegative(int id) {
		// TODO Auto-generated method stub
		Comment cmt = commentRepository.findOne(id);
		if(cmt.getIsNegative() == true) {
			cmt.setIsNegative(false);
		} else {
			cmt.setIsNegative(true);
		}
		
		commentRepository.save(cmt);
		
	}

	@Override
	public boolean isExists(int id) {
		// TODO Auto-generated method stub
		return commentRepository.exists(id);
	}

	@Override
	public List<Comment> getListCommentOfHotel(int hotelId) {
		// TODO Auto-generated method stub
		return commentRepository.getListCommentOfHotel(hotelId);
	}

	@Override
	public Comment getCommentByBooking(int id) {
		// TODO Auto-generated method stub
		return commentRepository.getCommentOfBooking(id);
	}

}
