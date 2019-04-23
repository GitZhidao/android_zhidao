package com.example.dxnima.zhidao.bean.table;

import com.example.dxnima.zhidao.bean.ListBaseResp;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by DXnima on 2019/4/19.
 */
@Entity
public class GetSubject extends ListBaseResp<GetSubject> {

	private Integer id;

	private Integer userid;

	private Integer subid;

	private String categoryname;

	public String getCategoryname() {
		return this.categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public Integer getSubid() {
		return this.subid;
	}

	public void setSubid(Integer subid) {
		this.subid = subid;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Generated(hash = 1967765515)
	public GetSubject(Integer id, Integer userid, Integer subid, String categoryname) {
		this.id = id;
		this.userid = userid;
		this.subid = subid;
		this.categoryname = categoryname;
	}

	@Generated(hash = 1522015456)
	public GetSubject() {
	}

}