package com.xiaoyacz.kidsmath.game.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class PastersManager {
	private TextureAtlas atlas;
	public AtlasRegion bg;
	public Map<String, AtlasRegion> pasters;
	
	public PastersManager(TextureAtlas atlas){
		this.atlas=atlas;
		bg=atlas.findRegion("paster-bg");
		pasters=new HashMap<String, AtlasRegion>();
		pasters.put("yu6", atlas.findRegion("yu6"));
		pasters.put("banma1", atlas.findRegion("banma1"));
		pasters.put("ma", atlas.findRegion("ma"));
		pasters.put("gou2", atlas.findRegion("gou2"));
		pasters.put("daishu", atlas.findRegion("daishu"));
		pasters.put("yu7", atlas.findRegion("yu7"));
		pasters.put("konglong", atlas.findRegion("konglong"));
		pasters.put("xiongmao1", atlas.findRegion("xiongmao1"));
		pasters.put("jingyu", atlas.findRegion("jingyu"));
		pasters.put("huli3", atlas.findRegion("huli3"));
		pasters.put("yu1", atlas.findRegion("yu1"));
		pasters.put("niao8", atlas.findRegion("niao8"));
		pasters.put("woniu", atlas.findRegion("woniu"));
		pasters.put("laohu1", atlas.findRegion("laohu1"));
		pasters.put("kaola2", atlas.findRegion("kaola2"));
		pasters.put("lu2", atlas.findRegion("lu2"));
		pasters.put("houzi2", atlas.findRegion("houzi2"));
		pasters.put("gou", atlas.findRegion("gou"));
		pasters.put("bianfu", atlas.findRegion("bianfu"));
		pasters.put("niao3", atlas.findRegion("niao3"));
		pasters.put("niao5", atlas.findRegion("niao5"));
		pasters.put("shayu", atlas.findRegion("shayu"));
		pasters.put("she", atlas.findRegion("she"));
		pasters.put("huli1", atlas.findRegion("huli1"));
		pasters.put("houzi1", atlas.findRegion("houzi1"));
		pasters.put("eyu1", atlas.findRegion("eyu1"));
		pasters.put("luotuo", atlas.findRegion("luotuo"));
		pasters.put("lu1", atlas.findRegion("lu1"));
		pasters.put("haishi", atlas.findRegion("haishi"));
		pasters.put("konglong2", atlas.findRegion("konglong2"));
		pasters.put("nainiu1", atlas.findRegion("nainiu1"));
		pasters.put("yang", atlas.findRegion("yang"));
		pasters.put("qingwa2", atlas.findRegion("qingwa2"));
		pasters.put("nainiu2", atlas.findRegion("nainiu2"));
		pasters.put("qie2", atlas.findRegion("qie2"));
		pasters.put("yu5", atlas.findRegion("yu5"));
		pasters.put("jingyu2", atlas.findRegion("jingyu2"));
		pasters.put("xingxing", atlas.findRegion("xingxing"));
		pasters.put("she2", atlas.findRegion("she2"));
		pasters.put("ma3", atlas.findRegion("ma3"));
		pasters.put("zhu1", atlas.findRegion("zhu1"));
		pasters.put("beijixiong", atlas.findRegion("beijixiong"));
		pasters.put("niao7", atlas.findRegion("niao7"));
		pasters.put("daxiang2", atlas.findRegion("daxiang2"));
		pasters.put("xiongmao2", atlas.findRegion("xiongmao2"));
		pasters.put("zhangyu1", atlas.findRegion("zhangyu1"));
		pasters.put("xiniu", atlas.findRegion("xiniu"));
		pasters.put("eyu2", atlas.findRegion("eyu2"));
		pasters.put("daxiang", atlas.findRegion("daxiang"));
		pasters.put("niao", atlas.findRegion("niao"));
		pasters.put("niao2", atlas.findRegion("niao2"));
		pasters.put("kaola", atlas.findRegion("kaola"));
		pasters.put("niao9", atlas.findRegion("niao9"));
		pasters.put("konglong4", atlas.findRegion("konglong4"));
		pasters.put("wugui", atlas.findRegion("wugui"));
		pasters.put("niao6", atlas.findRegion("niao6"));
		pasters.put("niao4", atlas.findRegion("niao4"));
		pasters.put("xia2", atlas.findRegion("xia2"));
		pasters.put("laoshu2", atlas.findRegion("laoshu2"));
		pasters.put("huanxiong2", atlas.findRegion("huanxiong2"));
		pasters.put("songshu", atlas.findRegion("songshu"));
		pasters.put("shizi", atlas.findRegion("shizi"));
		pasters.put("hema1", atlas.findRegion("hema1"));
		pasters.put("haitun2", atlas.findRegion("haitun2"));
		pasters.put("qie3", atlas.findRegion("qie3"));
		pasters.put("dishu", atlas.findRegion("dishu"));
		pasters.put("citun", atlas.findRegion("citun"));
		pasters.put("yu4", atlas.findRegion("yu4"));
		pasters.put("ji2", atlas.findRegion("ji2"));
		pasters.put("songshu2", atlas.findRegion("songshu2"));
		pasters.put("qie1", atlas.findRegion("qie1"));
		pasters.put("qingwa", atlas.findRegion("qingwa"));
		pasters.put("tu", atlas.findRegion("tu"));
		pasters.put("yu2", atlas.findRegion("yu2"));
		pasters.put("ya", atlas.findRegion("ya"));
		pasters.put("laoshu", atlas.findRegion("laoshu"));
		pasters.put("wugui2", atlas.findRegion("wugui2"));
		pasters.put("huli2", atlas.findRegion("huli2"));
		pasters.put("xiaoxiong2", atlas.findRegion("xiaoxiong2"));
		pasters.put("maotouying", atlas.findRegion("maotouying"));
		pasters.put("yu3", atlas.findRegion("yu3"));
		pasters.put("pangxie", atlas.findRegion("pangxie"));
		pasters.put("lu3", atlas.findRegion("lu3"));
		pasters.put("mao2", atlas.findRegion("mao2"));
		pasters.put("xia1", atlas.findRegion("xia1"));
		pasters.put("xiaoxiong", atlas.findRegion("xiaoxiong"));
		pasters.put("hema2", atlas.findRegion("hema2"));
		pasters.put("mao1", atlas.findRegion("mao1"));
		pasters.put("ji1", atlas.findRegion("ji1"));
		pasters.put("wuzei", atlas.findRegion("wuzei"));
		pasters.put("laohu2", atlas.findRegion("laohu2"));
		pasters.put("ma2", atlas.findRegion("ma2"));
		pasters.put("konglong3", atlas.findRegion("konglong3"));
		pasters.put("zhu2", atlas.findRegion("zhu2"));
		pasters.put("ya2", atlas.findRegion("ya2"));
		pasters.put("haitunguniang", atlas.findRegion("haitunguniang"));
		pasters.put("huanxiong", atlas.findRegion("huanxiong"));
		pasters.put("xie1", atlas.findRegion("xie1"));

	}
	
	public List<String> getRandomPasters(int size){
		List<String> list=new ArrayList<String>();
		list.addAll(pasters.keySet());
		Collections.shuffle(list);
		return list.subList(0, size);
	}
}
