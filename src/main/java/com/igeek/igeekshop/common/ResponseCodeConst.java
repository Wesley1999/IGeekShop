package com.igeek.igeekshop.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 王少刚
 * @Time 2019/3/7 11:43
 */

public class ResponseCodeConst {
	public static final Integer SUCCESS = 0;
	public static final Integer PASSWORD_NOT_SAME = 101;
	public static final Integer WRONG_PASSWORD = 102;
	public static final Integer WRONG_OLD_PASSWORD = 103;
	public static final Integer EMAIL_EXIST = 104;
	public static final Integer EMAIL_NOT_EXIST = 105;
	public static final Integer BRANCH_SCHOOL_NAME_EXIST = 106;
	public static final Integer BRANCH_SCHOOL_EXIST_MAINTAINER = 107;
	public static final Integer NOT_CHECKED = 108;
	public static final Integer NEED_LOGIN = 201;
	public static final Integer PERMISSION_DENIED = 202;
	public static final Integer ERROR_USER_ID = 203;
	public static final Integer ERROR_COURSE_ID = 204;
	public static final Integer ERROR_CLASSROOM_ID = 205;
	public static final Integer ERROR_TEACHER_ID = 206;
	public static final Integer ERROR_MAINTAINER_ID = 207;
	public static final Integer TEACHER_NOT_CHECKED = 208;
	public static final Integer MAINTAINER_NOT_CHECKED = 209;
	public static final Integer ERROR_CLASS_ID = 210;
	public static final Integer ERROR_HISTORY_CLASS_ID = 211;
	public static final Integer ERROR_STUDENT_ID = 212;
	public static final Integer ERROR_STUDENT_TYPE = 213;
	public static final Integer ERROR_GUARDIAN_ID = 214;
	public static final Integer ERROR_NOTICE_ID = 215;
	public static final Integer ERROR_EMAIL_TYPE = 216;
	public static final Integer ERROR_COURSE_AGE_ID = 217;
	public static final Integer ERROR_COURSE_LEVEL_ID = 218;
	public static final Integer ERROR_URL = 301;
	public static final Integer ERROR_FORM_DATA = 303;
	public static final Integer ERROR_STATUS = 304;
	public static final Integer ERROR_ATTENDANCE_STATUS = 305;
	public static final Integer ERROR_COURSE_STATUS = 306;
	public static final Integer NULL_RESULT = 401;
	public static final Integer MAINTAIN_CLASS_OF_SOME_BRANCH_SCHOOLS = 501;
	public static final Integer MAINTAIN_CLASS = 502;
	public static final Integer MAINTAIN_SCHOOL = 503;
	public static final Integer TEACHES_ONLINE_COURSE = 601;
	public static final Integer BRANCH_SCHOOL_NOT_SETTLED_CLASS_EXIST = 602;
	public static final Integer UNSETTLED_CLASS_EXIST = 603;
	public static final Integer BRANCH_SCHOOL_HISTORY_CLASS_EXIST = 604;
	public static final Integer ERROR_BRANCH_SCHOOL_ID = 701;
	public static final Integer ERROR_NUMBER_PARAM = 1001;
	public static final Integer ERROR_DATE_TIME_FORMAT = 1002;
	public static final Integer START_DATE_NOT_BEFORE_END_DATE = 1003;
	public static final Integer START_TIME_NOT_BEFORE_END_TIME = 1004;
	public static final Integer ERROR_DAY = 1005;
	public static final Integer TIME_LIMIT_INTERSECT = 1006;
	public static final Integer ERROR_TIME_ORDER = 1007;
	public static final Integer REQUIRED_PARAM_IS_EMPTY = 1101;
	public static final Integer CLASSROOM_NAME_EXIST = 1201;
	public static final Integer CLASS_USE_CLASSROOM = 1301;
	public static final Integer OCCUPIED_TIME_CANNOT_BE_REMOVE = 1302;
	public static final Integer CLASS_NAME_EXIST = 1401;
	public static final Integer COURSE_NAME_EXIST = 1402;
	public static final Integer ERROR_CLASS_NAME_SUFFIX_FORMAT = 1501;
	public static final Integer ERROR_SUBJECT_NAME_FORMAT = 1502;
	public static final Integer ERROR_LEVEL_FORMAT = 1503;
	public static final Integer ERROR_AGE_VALUE = 1504;
	public static final Integer ERROR_COURSE_NAME_SUFFIX_FORMAT = 1505;
	public static final Integer COURSE_NOT_ONLINE = 1601;
	public static final Integer MAINTAINER_NOT_MANAGE_THIS_BRANCH_SCHOOL = 1701;
	public static final Integer CURRENT_CLASS_STATUS_NOT_ALLOW_THIS_OPERATION = 1801;
	public static final Integer ERROR_LESSON_VALUE = 1802;
	public static final Integer ERROR_LESSON_NUM = 1803;
	public static final Integer CLASS_SCHEDULE_HAS_BEEN_GENERATED = 1804;
	public static final Integer CLASS_SCHEDULE_NOT_GENERATED = 1805;
	public static final Integer CLASS_STATUS_SHOULD_BE_STARTS = 1806;
	public static final Integer CLASSROOM_TIME_NOT_AVAILABLE = 1901;
	public static final Integer CLASSROOM_MIN_STU_NUM_NOT_COMPLIANT = 2001;
	public static final Integer CLASSROOM_TYPE_NOT_COMPLIANT = 2002;
	public static final Integer CLASSROOM_AREA_NOT_COMPLIANT = 2003;
	public static final Integer CLASSROOM_GROUND_FEATURE_NOT_COMPLIANT = 2004;
	public static final Integer CLASSROOM_ROOF_FEATURE_NOT_COMPLIANT = 2005;
	public static final Integer STUDENT_NOT_IN_THIS_CLASS = 2101;
	public static final Integer STUDENT_IN_THIS_CLASS = 2102;
	public static final Integer CLASS_STATUS_MODIFICATION_NOT_MEET_REQUIREMENT = 2201;
	public static final Integer CLASS_LAST_LESSON_NOT_END = 2202;
	public static final Integer CLASS_STATUS_NOT_ALLOW_DELETE = 2301;
	public static final Integer CLASS_HAS_STUDENT = 2401;
	public static final Integer ERROR_WEEK_INDEX = 2501;
	public static final Integer NO_MAKE_UP_OR_TRIAL_INFORMATION = 2601;
	public static final Integer STUDENT_HAS_APPLIED_FOR_MAKE_UP = 2602;
	public static final Integer STUDENT_HAS_APPLIED_THIS_CLASS_LESSON = 2603;
	public static final Integer STUDENT_PAID_IN_CLASS = 2701;
	public static final Integer STUDENT_AGE_NOT_MEET_REQUEST = 2801;
	public static final Integer THE_STU_NUM_IS_FULL = 2901;
	public static final Integer STUDENT_TIME_CONFLICT = 3001;
	public static final Integer STUDENT_HAS_APPLIED_MAKE_UP = 3101;
	public static final Integer THIS_LESSON_HAS_ENDED = 3201;
	public static final Integer STUDENT_ATTENDANT = 3301;
	public static final Integer MAKE_UP_TIME_TOO_LATE = 3401;
	public static final Integer TRIAL_TIME_RUN_OUT = 3501;
	public static final Integer TRAIL_CLASS_LESSON_NOT_IN_ONE_MONTH = 3601;
	public static final Integer ERROR_IDENTITY_PARAM = 3701;
	public static final Integer NO_RECIPIENT = 3801;
	public static final Integer CANNOT_SEND_TO_YOURSELF = 3802;
	public static final Integer VALUE_IS_TOO_LARGE = 3803;
	public static final Integer ERROR_SEX_VALUE = 3901;
	public static final Integer ERROR_SINGLE_LESSON_DURATION = 4001;
	public static final Integer ERROR_PARAM_FORMAT = 4101;
	public static final Integer USED_COURSE_LEVEL_CANNOT_BE_REMOVED = 4201;
	public static final Integer CLASS_USE_COURSE = 4301;
	public static final Integer ERROR_CLASSROOM_NAME = 4401;
	public static final Integer TEACHER_HAS_CHECKED_IN = 4501;
	public static final Integer ERROR_LONGITUDE_VALUE = 4601;
	public static final Integer ERROR_LATITUDE_VALUE = 4602;
	public static final Integer CHECK_IN_IS_NOT_ALLOWED_AT_THE_CURRENT_TIME = 4701;
	public static final Integer CANNOT_CHECK_IN_AGAIN = 4702;
	public static final Integer NEED_AT_LEAST_ONE_MAINTAINER = 4801;
	public static final Integer DATABASE_EXCEPTION = 9998;
	public static final Integer NOT_COMPLETE = 9999;


	public static Map<Integer, String> responseMessage = new HashMap<Integer, String>() {{
		put(PASSWORD_NOT_SAME, "The password entered twice is different.");    // 两次输入的密码不同
		put(WRONG_PASSWORD, "The password is wrong.");    // 密码错误
		put(WRONG_OLD_PASSWORD, "The old password is wrong.");   // 旧密码错误
		put(EMAIL_EXIST, "The email already exists.");  // 邮箱已存在
		put(EMAIL_NOT_EXIST, "The email doesn't exist.");  // 邮箱不存在
		put(BRANCH_SCHOOL_NAME_EXIST, "Branch school name already exists.");   // 分校名称已存在
		put(BRANCH_SCHOOL_EXIST_MAINTAINER, "Branch school has support staff."); // 分校有支撑人员
		put(NOT_CHECKED, "Not checked."); // 没有通过审核
		put(NEED_LOGIN, "Need login.");    // 需要登录
		put(PERMISSION_DENIED, "Permission denied."); // 没有权限
		put(ERROR_USER_ID, "Error userId."); // 操作了错误的userId
		put(ERROR_COURSE_ID, "Error courseId."); // 操作了错误的courseId
		put(ERROR_CLASSROOM_ID, "Error classroomId.");   // 操作了错误的classroomId
		put(ERROR_TEACHER_ID, "Error teacherId.");   // 操作了错误的teacherId
		put(ERROR_MAINTAINER_ID, "Error maintainerId."); // 操作了错误的maintainerId
		put(TEACHER_NOT_CHECKED, "Teacher is not checked.");  // 授课老师未通过审核
		put(MAINTAINER_NOT_CHECKED, "Maintainer is not checked.");   // 支撑人员未通过审核
		put(ERROR_CLASS_ID, "Error classId.");   // 操作了错误的classId
		put(ERROR_HISTORY_CLASS_ID, "Error historyClassId.");    // 操作了错误的historyClassId
		put(ERROR_STUDENT_ID, "Error studentId.");   // 操作了错误的studentId
		put(ERROR_STUDENT_TYPE, "Error studentType."); // studentType参数有误
		put(ERROR_GUARDIAN_ID, "Error guardianId."); // 操作了错误的guardianId
		put(ERROR_NOTICE_ID, "Error noticeId."); // 操作了错误的noticeId
		put(ERROR_EMAIL_TYPE, "Error emailType."); // emailType参数有误
		put(ERROR_COURSE_AGE_ID, "Error courseAgeId."); // courseAgeId参数有误
		put(ERROR_COURSE_LEVEL_ID, "Error courseLevelId."); // courseLevelId参数有误
		put(ERROR_URL, "Error URL.");    // URL错误
		put(ERROR_FORM_DATA, "Error form data."); // 表单数据错误
		put(ERROR_STATUS, "Error status.");    // status参数有误
		put(ERROR_ATTENDANCE_STATUS, "Error attendanceStatus.");   // attendanceStatus参数有误
		put(ERROR_COURSE_STATUS, "Error courseStatus.");   // courseStatus参数有误
		put(NULL_RESULT, "Null result."); // 查询结果为空
		put(MAINTAIN_CLASS_OF_SOME_BRANCH_SCHOOLS, "The update failed because the support staff is a class maintainer for some branch schools.");  // 更新失败，因为支撑人员是某些分校的班级的辅导员
		put(MAINTAIN_CLASS, "The update failed because the support staff is a class maintainer.");    // 操作失败，因为支撑人员是某些班级的辅导员
		put(MAINTAIN_SCHOOL, "The update failed because the support staff manages some branch schools."); // 操作失败，因为支撑人员管理了某些分校
		put(TEACHES_ONLINE_COURSE, "The update failed because the teacher has some courses.");   // 操作失败，因为该授课老师有课程
		put(BRANCH_SCHOOL_NOT_SETTLED_CLASS_EXIST, "The update failed because the branch school has some classes not settled.");    // 操作失败，因为该分校有未结算的班级
		put(UNSETTLED_CLASS_EXIST, "The update failed because some classes is not settled.");    // 操作失败，存在未结算的班级
		put(BRANCH_SCHOOL_HISTORY_CLASS_EXIST, "The update failed because the branch has some classes settled.");    // 操作失败，因为该分校有已结算的班级
		put(ERROR_BRANCH_SCHOOL_ID, "Error branchSchoolId."); // 分校id不存在
		put(ERROR_NUMBER_PARAM, "Error 'number' param.");    // number参数有误
		put(ERROR_DATE_TIME_FORMAT, "Error date or time param.");   // 时间或日期格式有误
		put(START_DATE_NOT_BEFORE_END_DATE, "The start data is not before end date.");    // 起始日期不在结束时间之前
		put(START_TIME_NOT_BEFORE_END_TIME, "The start time is not before end time.");    // 起始时间不在结束时间之前
		put(ERROR_DAY, "Error 'day' param.");    // 星期值错误
		put(TIME_LIMIT_INTERSECT, "The time period intersects"); // 时间段相交
		put(ERROR_TIME_ORDER, "The time order is wrong.");    // 时间顺序有误
		put(REQUIRED_PARAM_IS_EMPTY, "The required parameter is empty.");  // 必需提供的参数为空
		put(CLASSROOM_NAME_EXIST, "Classroom name exists.");   // 教室名称已存在
		put(CLASS_USE_CLASSROOM, "The classroom is used by some classes.");  // 存在使用此教室的班级
		put(OCCUPIED_TIME_CANNOT_BE_REMOVE, "Occupied time cannot be removed."); // 已经被班级占用的时间不能被移除
		put(CLASS_NAME_EXIST, "Class name exists.");   // 班级名称已存在
		put(COURSE_NAME_EXIST, "Course name exists.");   // 课程名称已存在
		put(ERROR_CLASS_NAME_SUFFIX_FORMAT, "The class name suffix format is wrong.");   // 班级名称后缀格式错误
		put(ERROR_SUBJECT_NAME_FORMAT, "The subject name suffix format is wrong.");   // 班级名称后缀格式错误
		put(ERROR_LEVEL_FORMAT, "The level format is wrong.");   // 班级名称后缀格式错误
		put(ERROR_AGE_VALUE, "The age is wrong.");   // age值错误
		put(ERROR_COURSE_NAME_SUFFIX_FORMAT, "The course name suffix is wrong.");   // 课程名称后缀错误
		put(COURSE_NOT_ONLINE, "The course is not online.");    // 课程未上架
		put(MAINTAINER_NOT_MANAGE_THIS_BRANCH_SCHOOL, "The maintainer cannot manage this branch school.");   // 该支撑人员不管理此分校
		put(CURRENT_CLASS_STATUS_NOT_ALLOW_THIS_OPERATION, "Current class status not allow this operation.");   // 当前班级状态不允许进行此操作
		put(ERROR_LESSON_VALUE, "Error 'lesson' value.");   // lesson值有误
		put(ERROR_LESSON_NUM, "Error 'lesson' number.");    // lesson数量有误
		put(CLASS_SCHEDULE_HAS_BEEN_GENERATED, "The class schedule has been generated.");  // 班级课表已生成
		put(CLASS_SCHEDULE_NOT_GENERATED, "The class schedule is not generated.");   // 班级课表未生成
		put(CLASS_STATUS_SHOULD_BE_STARTS, "Class status should be 'start'.");   // 班级课表未生成
		put(CLASSROOM_TIME_NOT_AVAILABLE, "Classroom cannot be used in this time.");    // 该教室当前时间不可用
		put(CLASSROOM_MIN_STU_NUM_NOT_COMPLIANT, "Classroom min stu num does not meet the demand.");  // 教室最小容纳人数不符合要求
		put(CLASSROOM_TYPE_NOT_COMPLIANT, "Classroom type does not meet the demand."); // 教室类型不符合要求
		put(CLASSROOM_AREA_NOT_COMPLIANT, "Classroom area does not meet the demand."); // 教室面积不符合要求
		put(CLASSROOM_GROUND_FEATURE_NOT_COMPLIANT, "Classroom ground feature does not meet the demand."); // 教室地面特征不符合要求
		put(CLASSROOM_ROOF_FEATURE_NOT_COMPLIANT, "Classroom roof feature does not meet the demand.");   // 教室天顶特征不符合要求
		put(STUDENT_NOT_IN_THIS_CLASS, "The student is not in this class."); // 学员不在此班级中
		put(STUDENT_IN_THIS_CLASS, "The student is in this class.");  // 学员在此班级中
		put(CLASS_STATUS_MODIFICATION_NOT_MEET_REQUIREMENT, "Class status modification does not meet the demand."); // 班级状态修改不符合要求
		put(CLASS_LAST_LESSON_NOT_END, "Class last lesson not end.");   // 班级最后一次课未结束
		put(CLASS_STATUS_NOT_ALLOW_DELETE, "The class status does not allow delete.");  // 此状态的班级不允许删除
		put(CLASS_HAS_STUDENT, "Class has student.");   // 班级存在学员
		put(ERROR_WEEK_INDEX, "Error 'weekIndex' param."); // weekIndex参数有误
		put(NO_MAKE_UP_OR_TRIAL_INFORMATION, "No make up or trial information.");   // 没有缺勤补课信息
		put(STUDENT_HAS_APPLIED_FOR_MAKE_UP, "Student has applied for make up.");   // 此缺勤已申请补课
		put(STUDENT_HAS_APPLIED_THIS_CLASS_LESSON, "Student has applied for this lesson. (make up or trial)");  // 已申请该班级此次课程补课或试听
		put(STUDENT_PAID_IN_CLASS, "Student has paid.");    // 学员已缴费
		put(STUDENT_AGE_NOT_MEET_REQUEST, "Student age does not meet the demand."); // 学员年龄不符合要求
		put(THE_STU_NUM_IS_FULL, "The stu num is full."); // 学员人数已满
		put(STUDENT_TIME_CONFLICT, "Student time conflict.");   // 学员时间冲突
		put(STUDENT_HAS_APPLIED_MAKE_UP, "The student has applied for make-up, please cancel the application first."); // 学员已申请补课，请先取消申请
		put(THIS_LESSON_HAS_ENDED, "This lesson is ended.");   // 这节课已结束
		put(STUDENT_ATTENDANT, "Student is attendant."); // 学生出勤
		put(MAKE_UP_TIME_TOO_LATE, "Make-up time out.");   // 补课时间间隔超过限制
		put(TRIAL_TIME_RUN_OUT, "Trail time run out."); // 试听次数已用完
		put(TRAIL_CLASS_LESSON_NOT_IN_ONE_MONTH, "The class time for the trial is not within the next month.");   // 试听的班级课时不在未来一个月以内
		put(ERROR_IDENTITY_PARAM, "Error identity.");  // identity参数有误
		put(NO_RECIPIENT, "No recipient."); // 没有接收者
		put(CANNOT_SEND_TO_YOURSELF, "Cannot send to yourself.");    // 不能发送给自己
		put(VALUE_IS_TOO_LARGE, "Value is too large.");    // 数值太大
		put(ERROR_SEX_VALUE, "Error sex value.");    // 性别值有误
		put(ERROR_SINGLE_LESSON_DURATION, "Error single lesson duration.");    // singleLessonDuration值有误
		put(ERROR_PARAM_FORMAT, "Error param format.");    // 参数格式有误
		put(USED_COURSE_LEVEL_CANNOT_BE_REMOVED, "Used courseLevel cannot be removed");
		put(CLASS_USE_COURSE, "The course is used by some classes");
		put(ERROR_CLASSROOM_NAME, "Error classroomName.");
		put(TEACHER_HAS_CHECKED_IN, "Teacher has checked in.");	// 老师已签到
		put(ERROR_LONGITUDE_VALUE, "Error longitude value.");	// 经度值错误
		put(ERROR_LATITUDE_VALUE, "Error latitude value.");	// 纬度值错误
		put(CHECK_IN_IS_NOT_ALLOWED_AT_THE_CURRENT_TIME, "Check-in is not allowed at the current time.");   // 当前时间不允许签到（只能给当天的课签到）
		put(CANNOT_CHECK_IN_AGAIN, "Cannot check in again.");   // 已签到，不能重复签到
		put(NEED_AT_LEAST_ONE_MAINTAINER, "Need at least one maintainer");  // 分校至少要有一个支撑人员
		put(DATABASE_EXCEPTION, "Database exception.");   // 数据库异常
		put(NOT_COMPLETE, "The API is not completed.");   // 此接口尚未完成

	}};


	public static String getResponseMessageByResponseCode(Integer responseCode) {
		return responseMessage.get(responseCode);
	}
}
