package com.sist.vo;

import java.util.*;

import lombok.Data;

@Data
public class GymReplyVO {
	private int no, gno;
	private String userId, userName, msg, dbday;
	private Date regdate;
}
