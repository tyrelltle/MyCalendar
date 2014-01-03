package com.tianshao.mycalendar.repository;
/*
 * generic app engine data access object
 * */
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.tianshao.mycalendar.model.Event;
import com.tianshao.mycalendar.model.IModel;

public class DAOAppEngineImpl implements DAO {
    DatastoreService ds = DatastoreServiceFactory.getDatastoreService();

	@Override
	public ArrayList<? extends IModel>  getList(Class c) throws InstantiationException, IllegalAccessException {
		IModel model=((IModel) c.newInstance());
		Query q=new Query(model.category());
		PreparedQuery pq=ds.prepare(q);
		List<Entity> ret=pq.asList(FetchOptions.Builder.withDefaults());
		Iterator<Entity> i=ret.iterator();
		Field[] fs=this.getFields(c);
		ArrayList<IModel> e=new ArrayList<IModel>();
		while(i.hasNext()){
			Entity entity=i.next();
			
			
			for(int j=0;j<fs.length;j++){
				Field f=fs[j];
				f.set(model, entity.getProperty(f.getName()));
			}
			/*new Event((String)entity.getProperty("title"),
					 (String)entity.getProperty("loc"),
					 (String)entity.getProperty("detail"))*/
			model.id= entity.getKey().getId();
			e.add(model);
			model=((IModel) c.newInstance());
		}
		return e;
	}

	@Override
	public void update(IModel e,long id) throws IllegalArgumentException, IllegalAccessException {
		PreparedQuery pq=queryById(id,e.category());
		Entity result=pq.asSingleEntity();
		Field[] fs=this.getFields(e.getClass());
		for(int i=0;i<fs.length;i++){
			Field f=fs[i];
			result.setProperty(f.getName(), f.get(e));
		}
		/*result.setProperty("title", e.title);
		result.setProperty("loc", e.loc);
		result.setProperty("detail", e.detail);*/
		this.ds.put(result);
	}


	@Override
	public void del(long id,Class c) throws InstantiationException, IllegalAccessException {
		Key computedKey=KeyFactory.createKey(((IModel) c.newInstance()).category(), id);
		ds.delete(computedKey);
		
	}

	@Override
	public IModel get(long id,Class c) throws IllegalArgumentException, IllegalAccessException, InstantiationException {
		IModel ret=(IModel) c.newInstance();
		PreparedQuery pq=queryById(id,ret.category());
		Entity result=pq.asSingleEntity();
		if(result==null)
			return null;
		
		Field[] fs=this.getFields(c);
		for(int i=0;i<fs.length;i++){
			Field f=fs[i];
			Object val=result.getProperty(f.getName());
			if(val==null)
				continue;
			f.set(ret, val);

		}
		ret.id=id;
		return ret;
		/*
		return new Event((String)result.getProperty("title"),
						 (String)result.getProperty("loc"),
						 (String)result.getProperty("detail"));
	*/
	}

	@Override
	public long add(IModel e) throws IllegalArgumentException, IllegalAccessException {
		Entity newe=new Entity(e.category());
		Field[] fs=this.getFields(e.getClass());
		for(int i=0;i<fs.length;i++){
			
			Field f=fs[i];
			if(f.getName().equals("id"))
				continue;//dont set id field
			newe.setProperty(f.getName(),f.get(e));
		}
	 /*   newe.setProperty("title", e.title);
	    newe.setProperty("loc", e.loc);
	    newe.setProperty("detail", e.detail);*/
	    Key k=ds.put(newe);
		return  k.getId();
	}
	
	

	private PreparedQuery queryById(long id, String category) {
		Key computedKey=KeyFactory.createKey(category, id);
		Query q=new Query(category).addFilter(Entity.KEY_RESERVED_PROPERTY,
                Query.FilterOperator.EQUAL,
                computedKey);
		PreparedQuery pq=ds.prepare(q);
		return pq;
	}
	private  Field[] getFields(Class c){
		return c.getDeclaredFields();
	}
	
	
}
