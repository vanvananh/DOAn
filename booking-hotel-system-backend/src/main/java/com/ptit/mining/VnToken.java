package com.ptit.mining;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import vn.hus.nlp.tokenizer.VietTokenizer;

public class VnToken {

	private static String[] bagOfWord = { "bãi", "bé", "bình_thường", "bảo_vệ", "bất_tiện", "bẩn", "bể", "bị", "bữa",
			"cao", "chu_đáo", "chung", "chuyên_nghiệp", "chuyến", "chút", "chăn", "chơi", "chất_lượng", "chắc_chắn",
			"chỉ", "chị", "chọn", "chợ", "chứ", "con", "cách", "cách_âm", "cái", "còn", "cùng", "cũ", "cũng", "cả",
			"cảm_giác", "cảm_thấy", "cảm_ơn", "cần", "cửa", "di_chuyển", "do", "du_lịch", "dù", "dùng", "dơ", "dễ",
			"dễ_thương", "dịch_vụ", "dịp", "dọn", "ga", "gia_đình", "giá", "giá_cả", "giường", "giờ", "good", "gì",
			"gần", "gọi", "hay", "hotel", "hài_lòng", "hàng", "hình", "hôm", "hơi", "hơn", "hết", "hỏi", "hồ", "hỗ_trợ",
			"hợp_lý", "in", "khach", "khu", "khá", "khác", "khách", "khách_sạn", "khó", "khó_chịu", "không_",
			"không_gian", "không_ngon", "không_sạch", "không_tốt", "khăn", "kém", "kỳ_nghỉ", "luôn", "làm", "lên",
			"lúc", "lạ", "lại", "lạnh", "lần", "lắm", "lễ_tân", "lịch_sự", "lớn", "lựa_chọn", "muốn", "muỗi", "mà",
			"mát", "máy_lạnh", "mình", "món", "mùi", "mất", "mấy", "mặc_dù", "mọi", "mỗi", "mới", "mức", "nghĩ", "nghỉ",
			"ngon", "ngoài", "ngày", "ngủ", "nhan", "nhiều", "nhiệt", "nhiệt_tình", "nhà", "nhà_hàng", "nhân_viên",
			"nhìn", "nhưng", "nhất", "nhất_định", "nhận", "nhỏ", "những", "nào", "này", "nên", "nóng", "nơi", "nước",
			"nằm", "nếu", "nội_thất", "nữa", "ok", "phong", "phòng", "phòng_ốc", "phù_hợp", "phải", "phố", "phục_vụ",
			"quay", "quay_lại", "quá", "quán", "rẻ", "rộng", "rộng_rãi", "sach", "san", "sang", "sao", "se", "sáng",
			"sạch", "sạch_sẽ", "sạn", "sẽ", "số", "sớm", "the", "thiếu", "thoáng", "thoải_mái", "thuê", "thuận_lợi",
			"thuận_tiện", "thành_phố", "thái_độ", "thân_thiện", "thêm", "thích", "thôi", "thấy", "thật", "thể", "thức",
			"tiếng", "tiếp_tân", "tiền", "tiện", "tiện_nghi", "to", "toilet", "trung_tâm", "trả", "trải", "trừ", "tuy",
			"tuy_nhiên", "tuyệt", "tuyệt_vời", "tìn", "tạm", "tất_cả", "tầm", "tầng", "tắm", "tệ", "tối", "tốt", "tới",
			"uống", "vien", "view", "vui_vẻ", "về", "vệ_sinh", "vị_trí", "wifi", "xa", "xe", "xe_máy", "xuống",
			"yên_tĩnh", "yêu_cầu", "yếu", "ít", "điểm", "điện", "đáng", "đây", "đêm", "đôi", "đúng", "đưa", "được",
			"đầu", "đầy_đủ", "đặc_biệt", "đẹp", "đều", "địa_điểm", "đổi", "ồn", "ổn"};

	public static String[] tokentizer(String s) {
		VietTokenizer token;
		token = new VietTokenizer();
		String[] r = token.tokenize(s);
		return r;
	}

	public static void writeFile(String[] s) throws IOException {
//		StringBuilder st = new StringBuilder(s);
		FileOutputStream fos = new FileOutputStream("F:/booking-hotel-system-backend/data/test.dat");

		DataOutputStream dos = new DataOutputStream(fos);
		String line = "0";
		int j = 1;
		String str = s[0];
		String newStr = str.replace("không ", "không_");
		String newStr1 = newStr.replace("quay lại", "quay_lại");
		System.out.println(newStr1);
		String[] spl = newStr1.split(" ");
		for (String c : bagOfWord) {
			int i = 0;
			for (String in : spl) {
				if (c.equals(in)) {
					i++;
				}
			}
//			}
			if (i > 0) {
				String fi = " " + j + ":" + i;
				line = line.concat(fi);
//				dos.write(fi.getBytes());
			}
			j++;
		}

//		dos.write("#".getBytes());
		line = line.concat("#");
		dos.write(line.getBytes());
		dos.write("\r\n".getBytes());
		dos.write(line.getBytes());
		dos.close();

	}

	public static void writeFileTrain(String[] s, boolean isNegative) throws IOException {
//		StringBuilder st = new StringBuilder(s);
		FileOutputStream fos = new FileOutputStream("F:/booking-hotel-system-backend/data/final.dat", true);

		DataOutputStream dos = new DataOutputStream(fos);
		String line;
		if (isNegative) {
			line = "-1";
		} else {
			line = "1";
		}

		int j = 1;
		String str = s[0];
		String[] spl = str.split(" ");
		for (String c : bagOfWord) {
			int i = 0;
			for (String in : spl) {
				if (c.equals(in)) {
					i++;
				}
			}
//			}
			if (i > 0) {
				String fi = " " + j + ":" + i;
				line = line.concat(fi);
//				dos.write(fi.getBytes());
			}
			j++;
		}

//		dos.write("#".getBytes());
		line = line.concat("#");
		System.out.println(line);
		dos.write(line.getBytes());
		dos.write("\r\n".getBytes());
		dos.close();

	}

	public static boolean isNagative(String comment) throws IOException {
		String[] tokenn = tokentizer(comment);
		writeFile(tokenn);
		Scanner sc = new Scanner(new File("F:/booking-hotel-system-backend/data/predictions"));
		float i = 0;

		i = sc.nextFloat();
		if (i < 0)
			return true;
		return false;
	}
}
