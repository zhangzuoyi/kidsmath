package com.xiaoyacz.kidsmath.game.util;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Json;
import com.xiaoyacz.kidsmath.game.objects.Paster;

public class ObtainedPastersManager {
	private List<Paster> pasters;
	public static ObtainedPastersManager instance=new ObtainedPastersManager();
	
	private ObtainedPastersManager(){
		pasters=new ArrayList<Paster>();
	}
	
	public void addPaster(Paster paster){
		pasters.add(paster);
	}
	
	public List<Paster> getPasters(){
		return pasters;
	}
	
	public void save(){
		Preferences prefs = Gdx.app.getPreferences("prefs");
		Json json = new Json();
		List<PasterJson> list=new ArrayList<PasterJson>();
		for(Paster paster: pasters){
			PasterJson pj=new PasterJson();
			pj.setKey(paster.getKey());
			pj.setX(paster.getImage().getX());
			pj.setY(paster.getImage().getY());
			list.add(pj);
		}
		
		prefs.putString("pasters", json.toJson(list));
		prefs.flush();
	}
	
	public void load(){
		Preferences prefs = Gdx.app.getPreferences("prefs");
		String str=prefs.getString("pasters", null);
		if(str != null){
			Json json = new Json();
			ArrayList<PasterJson> list=json.fromJson(ArrayList.class, PasterJson.class, str);
			for(PasterJson pj: list){
				pasters.add(Paster.newInstance(pj.key, pj.x, pj.y));
			}
		}
	}
	
	public static class PasterJson{
		private String key;
		private float x;
		private float y;
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public float getX() {
			return x;
		}
		public void setX(float x) {
			this.x = x;
		}
		public float getY() {
			return y;
		}
		public void setY(float y) {
			this.y = y;
		}
		
	}
}
