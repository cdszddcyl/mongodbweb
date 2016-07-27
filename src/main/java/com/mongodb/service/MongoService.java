package com.mongodb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mongodb.client.model.geojson.Position;
import com.mongodb.dao.MongoDAO;
import com.mongodb.model.Person;
import com.mongodb.util.PageInfo;
import com.mongodb.util.Parameter;

/**
 * 
 * @author StoneCai 2016年07月25日16:06:16 添加 用于处理mongo的工具服务
 */
@Service
@SuppressWarnings("rawtypes")
public class MongoService {

	@Autowired
	private MongoDAO<Person> dao;

	/**
	 * Stone.Cai 2016年07月25日16:26:38 添加 插入
	 */
	public void insert(Person position) {
		dao.insertObject(position);
	}

	/**
	 * 
	 * @param position
	 *            2016年07月25日16:43:40 添加 插入批量
	 */
	public void insert(List<Person> position) {
		dao.insertObject(position);
	}

	/**
	 * Stone.Cai 2016年07月25日18:02:18 添加 删除
	 */
	public void delete(Person p) {
		dao.delete(p);
	}

	/**
	 * Stone.Cai 2016年07月26日10:11:07 添加 根据条件删除
	 */

	public void deleteById(String id, Class p) {
		dao.deleteById(id, p);
	}

	/**
	 * Stone.Cai 2016年07月26日10:11:57 添加 根据条件删除
	 */
	public void deleteForWhere(String[] colums, Object[] values, Class c) {
		dao.deleteForWhere(colums, values, c);
	}
	/**
	 * Stone.Cai
	 * 2016年07月26日14:27:22
	 * 添加
	 * 删除所有数据
	 */
	public void deleteAll(Class cls){
		dao.deleteAll(cls);
	}
	/**
	 * Stone.Cai
	 * 2016年07月26日16:07:28
	 * 添加
	 * 根据ID查询
	 */
	public List<Person> findAll(Class cls){
		List<Person> list= dao.findAll(cls);
		return list;
	}
	/**
	 * Stone.Cai
	 * 2016年07月26日16:25:48
	 * 添加
	 * 根据id获取一个数据值
	 */
	public Person findById(String id,Class cls){
		return dao.findById(id, cls);
	}
	/**
	 * Stone.Cai
	 * 2016年07月27日13:49:43
	 * 添加
	 * 模糊查询
	 */
	public PageInfo findPageInfo(PageInfo page,Query query,Class cls){
		return dao.findPage(page, query, cls);
	}

}
