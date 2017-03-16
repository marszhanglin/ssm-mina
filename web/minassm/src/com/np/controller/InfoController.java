package com.np.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.np.domain.Info;
import com.np.service.InfoService;
import com.np.utils.ResponseUtil;


@Controller
public class InfoController extends BaseController {

	@Resource
	private InfoService infoService;
	
	
	
	
	/**
	 * 获取主界面
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		List<Info> list = infoService.selectListInfos();
//		JSONArray jsonArray = JSONArray.fromObject(list);
//		JSONObject result = new JSONObject();
//		result.put("rows", jsonArray);
		
//		ResponseUtil.write(response, jsonArray);
//		System.out.println("index");
		return "/index";
		
	}

	
	@RequestMapping("/infolist")
	public void infolist(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("infolist");
		List<Info> list = infoService.selectListInfos();
		JSONArray jsonArray = JSONArray.fromObject(list);
//		JSONObject result = new JSONObject();
//		result.put("rows", jsonArray);
		
		ResponseUtil.write(response, jsonArray);
//		System.out.println("index");
//		return "/index";
		
	}

	
	
	
	
	
	
//
//	/**
//	 * 获取问题列表
//	 * @param request
//	 * @param response
//	 * @throws Exception
//	 */
//	@RequestMapping("/getQuestionList")
//	public void getQuestionList(HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//		
//		try {
//			request.setCharacterEncoding("utf-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		JSONObject result = new JSONObject();
//		List<Question> questionList = new ArrayList<Question>();
//		questionList =questionService.selectListQuestion();
//	//	System.out.println(questionList);
//		JSONArray jsonArray = JSONArray.fromObject(questionList);
//		result.put("msgid", "1");
//		result.put("msg", "成功获取");
//		result.put("rows", jsonArray);
//		ResponseUtil.write(response, result);
//	//	System.out.println(result);
//	}	
//	
//	@RequestMapping("/getppt")
//	public void getppt(HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//        
//    	List<Question> questionList = new ArrayList<Question>();
//		questionList =questionService.selectListQuestion();
//		SlideShow _slideShow = new SlideShow(); 
//		  File file = new File("E:\\text.ppt");
//	      FileInputStream fin=new FileInputStream(file);
//		 SlideShow ss=new SlideShow(new HSLFSlideShow(fin));
//	        Slide[] slides=ss.getSlides();
//		for (int i = 0; i < questionList.size(); i++) {
//				Slide slide= slides[0];
//				ArrayUtils.add(slides, slide);
//				System.out.println(slides.length);
//				JSONObject json = JSONObject.fromObject(questionList.get(i).getContent());
//				QuestionContent questionContent = (QuestionContent)JSONObject.toBean(json, QuestionContent.class);
//				TextRun[] runs1=slides[i].getTextRuns();
//				runs1[0].setText("驾考题目");
//				
//				int j = i+1;
//				if(questionContent.getChoiceList().get("A")!=null){
//				runs1[1].setText("第"+j+"题: "+questionContent.getTitle()+"\rA: "+questionContent.getChoiceList().get("A")
//						+"\rB: "+questionContent.getChoiceList().get("B")+"\rC: "+questionContent.getChoiceList().get("C")
//						+"\rD: "+questionContent.getChoiceList().get("D"));
//				}else {
//					runs1[1].setText("第"+j+"题: "+questionContent.getTitle()+"\rT: 正确\rF: 错误");
//				}
//				String strj = String.valueOf(j);
//				runs1[2].setText(strj);
//				System.out.println(runs1[0].getText());
//				System.out.println(runs1[1].getText());
//				System.out.println(runs1[2].getText());
//			}
//			// 输出文件  
//			ss.write(new FileOutputStream("E:\\text1.ppt"));  
//            
//		}
//    
//	
//	/**
//	 * 2016-03-16
//	 * wph
//	 * 比较答案是否错误
//	 * @param request
//	 * @param response
//	 * @throws Exception
//	 */
//	@RequestMapping("/getPoint")
//	public void getQuestionAnswer(HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//		
//		try {
//			request.setCharacterEncoding("utf-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		
//		String data = request.getParameter("data");
//		//	System.out.println(data);
//		Question question  = JSON.parseObject(data, Question.class);
//		JSONObject result = new JSONObject();
//		String[] answers = question.getAnswers().split(",");
//		int i = 0;
//		int point = 0;
//		String sql = " select answer,id  from et_question where  1 =1 ";
//		List<Question> questions1 =  new ArrayList<Question>();
//		for (String id : question.getIds().split(",")) {
//			int intid = Integer.valueOf(id);
//			if(i==0){
//				sql+=" and id = "+intid+" ";
//			}else {
//				sql+=" or id = "+intid+" ";
//			}
//			Question question2 = new Question();
//			question2.setId(intid);
//			question2.setAnswer(answers[i]);
//			questions1.add(question2);
//			
//			i++;
//		}
//		 /* Collections.sort(questions1, new Comparator<Question>() {
//	            public int compare(Question arg0, Question arg1) {
//	                return arg0.getId().compareTo(arg1.getId());
//	            }
//	        });*/
//	         
//	       /* for (Question p : questions1) {
//	            System.out.println(p.getId());
//	        }*/
//		//System.out.println(sql);
//		Question question2 = new Question();
//		question2.setSql(sql);
//		List<Question> questions = questionService.selectAnswerBySql(question2);
//		for(int j=0;j<questions.size();j++){
//			//System.out.println(questions.get(j).getId());
//			//System.out.println(questions1.get(j).getId());
//				if(questions.get(j).getAnswer().equals(questions1.get(j).getAnswer())){
//						point++;
//					}	
//		}		
//			result.put("piont", point);
//			ResponseUtil.write(response, result);
//	}	
//	
//	@RequestMapping("/getAnswerList")
//	public void getAnswerList(HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//		
//		try {
//			request.setCharacterEncoding("utf-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		
//		String data = request.getParameter("data");
//		//	System.out.println(data);
//		Question question  = JSON.parseObject(data, Question.class);
//		String[] answers = question.getAnswers().split(",");
//		Question question2 = new Question();
//		
//		//	System.out.println(question.getIds());
//		String sql = "select id, name, content, question_type_id, duration, points, "
//				+ "group_id, is_visible, create_time, "
//				+ "creator, last_modify, answer, "
//				+ "expose_times, right_times, wrong_times, "
//				+ " difficulty, analysis, reference, "
//				+ "examing_point, keyword  from et_question where  1 = 1 ";
//		int i=0;
//		for(String strid : question.getIds().split(",")){
//			int intid = Integer.valueOf(strid);
//			if(i==0){
//				sql+=" and id = "+intid+" ";
//			}else {
//				sql+=" or id = "+intid+" ";
//			}
//			i++;
//		}
//		question2.setSql(sql);
//		List<Question> questionList = questionService.selectAnswerBySql(question2);
//		int point=0;
//		for(int j=0;j<questionList.size();j++){
//			//System.out.println(questions.get(j).getId());
//			//System.out.println(questions1.get(j).getId());
//				if(questionList.get(j).getAnswer().equals(answers[j])){
//						point++;
//					}	
//		}		
//			JSONObject result = new JSONObject();
//			JSONArray jsonArray = JSONArray.fromObject(questionList);
//			result.put("msgid", "1");
//			result.put("msg", "成功获取");
//			result.put("rows", jsonArray);
//			result.put("piont", point);
//			ResponseUtil.write(response, result);
//		
//	}	
}
