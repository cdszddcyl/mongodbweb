package com.mongodb.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.mongodb.util.PageInfo;
import com.mongodb.util.Parameter;
import com.mongodb.util.ReflectionUtils;

/**
 * 
 * @author StoneCai 2016年07月25日16:07:08 添加 mongo的工具类
 */
@Component
@SuppressWarnings({"rawtypes","static-access","unchecked"})
public class MongoDAO<T> {

	@Autowired
	private MongoOperations tempLate;

	/**
	 * Stone.Cai 2016年07月25日16:12:39 添加 插入数据
	 */
	public void insertObject(T t) {
		tempLate.save(t);
	}

	/**
	 * Stone.Cai 2016年07月25日16:15:26 添加 批量插入
	 */
	public void insertObject(List<T> listT) {
		tempLate.insertAll(listT);
	}

	/**
	 * Stone.Cai 2016年07月25日17:08:04 添加 删除
	 */
	public void delete(T t) {
		tempLate.remove(t);
	}

	/**
	 * Stone.Cai 2016年07月26日09:36:56 添加 根据ID删除
	 */
	
	public void deleteById(String id,Class t) {
		Criteria criteria = Criteria.where("id").is(id);
		Query query = new Query(criteria);
		tempLate.remove(query,t);
	}

	/**
	 * Stone.Cai 2016年07月25日18:04:06 添加根据条件删除
	 */
	public void deleteForWhere(String[] colums, Object[] values,Class t) {
		//Assert.isNull(colums, "字段不能是空");
		//Assert.isNull(values, "数据不能是空");
		if (colums.length != values.length) {
			return;
		}
		Query query = new Query();
		for (int i = 0; i < colums.length; i++) {
			Criteria criteria = new Criteria();
			criteria.where(colums[i]).is(values[i]);
			query.addCriteria(criteria);
		}
		tempLate.remove(query,t);
	}
	
	/**
     * 根据条件删除
     */
    public void delete(Query query,Class cls) {
    	tempLate.remove(query, cls);
    }
    /**
     * 删除该collection 的所有的数据
     */
    public void deleteAll(Class cls) {
    	tempLate.dropCollection(cls);
    }
    
    /**
     * Stone.Cai
     * 2016年07月26日15:03:17
     * 添加
     * 改
     */
    public void update(Query query, Update update,Class t) {
    	tempLate.findAndModify(query, update, t);
    }
    /**
     * Stone.Cai
     * 2016年07月26日15:08:47
     * 添加
     * 
     */
    public List<T> findAll(Class cls){
        return tempLate.findAll(cls);
    }
    
    /**
     * 根据查询query 查找list
     * 
     * @param query
     * @return
     */
    public List<T> find(Query query,Class cls) {
        return tempLate.find(query,cls);
    }
	

	/**
	 * 获取collection的名字，默认是dao范型T的名字 <br/>
	 * 例如: StudentScoreDao extends MongodbDao <b>&lt;StudentScore&gt;</b> <br/>
	 * 则返回的名字是：<b>StudentScore</b>
	 * 
	 * @return
	 */
	private String getCollectionName(Class t) {
		return getEntityClass(t).getSimpleName();
	}
	/**
     * 按照字段排序 － 顺序  <br/>
     * @param query        查询条件  <br/>
     * @param properties   排序字段  <br/>
     * @return
     */
	public List<T> findWithOrderAsc(Query query,Class cls,String... properties){
        Sort sort = new Sort(Direction.ASC, properties);
        query.with(sort);
        return tempLate.find(query, cls);
    }
	/**
     * 按照字段排序 － 逆序 <br/>
     * @param query        查询条件  <br/>
     * @param properties   排序字段  <br/>
     * @return
     */
    public List<T> findWithOrderDesc(Query query, Class cls,String... properties){
        Sort sort = new Sort(Direction.DESC, properties);
        query.with(sort);
        return tempLate.find(query, cls);
    }
    
    /**
     * 根据查询query 查找一个对象
     * 
     * @param query
     * @return
     */
	public T findOne(Query query,Class cls) {
        return (T) tempLate.findOne(query, cls);
    }
	
	/**
     * 根据 id 查询对象
     * 
     * @param id
     * @return
     */
    public T findById(String id,Class cls) {
        return (T) tempLate.findById(id,cls);
    }
    
    /**
     * 查询分页  tips：［不要skip 太多的页数，如果跳过太多会严重影响效率。最大不要skip 20000页］
     * @param page
     * @param query
     * @return
     */
    public PageInfo findPage(PageInfo page, Query query,Class cla) {
        long count = this.count(query,cla);
        page.setTotal(count);
        int pageNumber = page.getCurrentPage();
        int pageSize = page.getPageSize();
        query.skip((pageNumber - 1) * pageSize).limit(pageSize);
        List list = this.find(query,cla);
        page.setList(list);
        return page;
    }
    
    public long count(Query query,Class cla) {
        return tempLate.count(query, cla);
    }
    
    
	/**
	 * 获取需要操作的实体类class <br/>
	 * 例如: StudentScore extends MongodbDao <b>&lt;StudentScore&gt;</b> <br/>
	 * 返回的是 <b>StudentScore</b> 的Class
	 * 
	 * @return
	 */
	private Class<T> getEntityClass(Class t) {
		return ReflectionUtils.getSuperClassGenricType(t);
	}

}
