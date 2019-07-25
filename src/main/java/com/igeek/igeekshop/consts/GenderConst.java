package com.igeek.igeekshop.consts;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 王少刚
 * @Date 2019/4/20 15:49
 */
public class GenderConst {
	public static final Integer MALE = 1;
	public static final Integer FEMALE = 2;
	public static final Integer OTHERS = 3;

	public static List<Integer> allGenders = new ArrayList<Integer>() {{
		add(MALE);
		add(FEMALE);
		add(OTHERS);
	}};

}
