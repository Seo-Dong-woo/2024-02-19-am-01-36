package com.sist.manager;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

import com.sist.vo.NewsVO;
/*
 *   {	"lastBuildDate":"Wed, 14 Feb 2024 15:16:53 +0900",	
 *   "total":600249,	"start":1,	"display":10,	
 *   "items":[		{			"title":"[금강칼럼] 설날, 바뀐 풍경",			"originallink":"https:\/\/www.ggilbo.com\/news\/articleView.html?idxno=1015209",			"link":"https:\/\/www.ggilbo.com\/news\/articleView.html?idxno=1015209",			"description":"함께 음식을 차려 점심을 먹었고 저녁은 <b>맛집<\/b>을 찾아 외식하러 나갔다. 식당에 마주 앉아 동생의 아내가 나지막히 말했다. &quot;예전 같았으면 집에서 명절 음식 준비하고 있을 시간에 이렇게 나오니 참 좋아요. 그런 생각을... ",			"pubDate":"Wed, 14 Feb 2024 15:02:00 +0900"		},		{			"title":"‘고물가’ 실속소비 뜬다…유통업계 자체브랜드 인기몰이",			"originallink":"https:\/\/www.dnews.co.kr\/uhtml\/view.jsp?idxno=202402141043069650932",			"link":"https:\/\/www.dnews.co.kr\/uhtml\/view.jsp?idxno=202402141043069650932",			"description":"피코크는 유명 <b>맛집<\/b>과 협업한 밀키트 등을 주력으로 하는 프리미엄 PB다. 2019년 연간 2500억원 규모였던 피코크 매출은 지난해 4200억원으로 68% 늘었다. 총 703개 상품을 취급한다. 지난해 가장 많은 매출을 올린... ",			"pubDate":"Wed, 14 Feb 2024 15:02:00 +0900"		},		{			"title":"'밤피꽃' 1위 독주 굳히기, 마지막회 확대 편성 결정",			"originallink":"",			"link":"https:\/\/n.news.naver.com\/mnews\/article\/415\/0000025384?sid=106",			"description":"&quot;이하늬 저세상 코믹 연기 미쳤다&quot;, &quot;이종원은 눈빛이 다했다&quot;, &quot;빠른 전개, 웃긴 연출까지 다 갖췄음&quot;, &quot;조연 배우들까지 연기 구멍이 하나도 없어서 보기 편함&quot;, &quot;여기 에필로그 <b>맛집<\/b>이네&quot; 등 반응을 보이고 있는 것.... ",			"pubDate":"Wed, 14 Feb 2024 15:00:00 +0900"		},		{			"title":"경북신문 2월 독자권익위원회, 취재 방향 제언과 의견 나눠",			"originallink":"http:\/\/www.kbsm.net\/news\/view.php?idx=422557",			"link":"http:\/\/www.kbsm.net\/news\/view.php?idx=422557",			"description":"황연신 위원은 SNS의 편협된 홍보에 밀린 경주의 ‘찐 <b>맛집<\/b>’과 ‘찐 명소’를 찾아 경주 현지인들의 시각으로 소개하는 취재와 보도를 제안했다.김정훈 부위원장은 APEC 경주 유치 100만 서명 운동 이후, 4월 총선 보도에... ",			"pubDate":"Wed, 14 Feb 2024 15:00:00 +0900"		},		{			"title":"서울 새 <b>맛집<\/b> 어딜까…미쉐린 ‘빕 구르망’ 발표",			"originallink":"https:\/\/www.sedaily.com\/NewsView\/2D5CVXH5J7",			"link":"https:\/\/n.news.naver.com\/mnews\/article\/011\/0004299478?sid=101",			"description":"미쉐린 가이드는 올해 서울 지역 ‘빕 구르망(Bib Gourmand)’ 레스토랑으로 57곳을 선정했다고 14일 밝혔다. 기존 51곳과 함께 △계월곰탕 △담택 △맷돌 △사루카메 △안암 △호라파 등 6개 식당이 새로 이름을 올렸다.... ",			"pubDate":"Wed, 14 Feb 2024 14:56:00 +0900"		},		{			"title":"'밤피꽃', 마지막회 확대 편성…독주 이어간다[공식]",			"originallink":"https:\/\/www.spotvnews.co.kr\/news\/articleView.html?idxno=658948",			"link":"https:\/\/n.news.naver.com\/mnews\/article\/477\/0000473530?sid=106",			"description":"&quot;이하늬 저세상 코믹 연기 미쳤다&quot;, &quot;이종원은 눈빛이 다했다&quot;, &quot;빠른 전개, 웃긴 연출까지 다 갖췄음&quot;, &quot;조연 배우들까지 연기 구멍이 하나도 없어서 보기 편함&quot;, &quot;여기 에필로그 <b>맛집<\/b>이네&quot; 등 다양한 반응들이 쏟아지고... ",			"pubDate":"Wed, 14 Feb 2024 14:55:00 +0900"		},		{			"title":"금토 1위 독주 ‘밤에 피는 꽃’, 마지막회 5분 확대편성 결정…15% 고지 찍을...",			"originallink":"https:\/\/www.sportsseoul.com\/news\/read\/1398965?ref=naver",			"link":"https:\/\/n.news.naver.com\/mnews\/article\/468\/0001030332?sid=106",			"description":"“이하늬 저세상 코믹 연기 미쳤다”, “이종원은 눈빛이 다했다”, ”빠른 전개, 웃긴 연출까지 다 갖췄음“, ”조연 배우들까지 연기 구멍이 하나도 없어서 보기 편함“, ”여기 에필로그 <b>맛집<\/b>이네“ 등 다양한... ",			"pubDate":"Wed, 14 Feb 2024 14:55:00 +0900"		},		{			"title":"대구 '뷰 <b>맛집<\/b>', 이월드 '라라카페'",			"originallink":"https:\/\/www.news1.kr\/photos\/view\/?6480825",			"link":"https:\/\/n.news.naver.com\/mnews\/article\/421\/0007350797?sid=102",			"description":"14일 대구 달서구 이월드를 찾은 시민들이 83타워 77층 전망대에 새롭게 단장한 '라라카페'에서 즐거운 시간을 보내고 있다. 이월드는 도심 전경을 한눈에 감상할 수 있는 77층 전망대에 이월드의 새로운 캐릭터... ",			"pubDate":"Wed, 14 Feb 2024 14:54:00 +0900"		},		{			"title":"대구 '뷰 <b>맛집<\/b>', 이월드 '라라카페'",			"originallink":"https:\/\/www.news1.kr\/photos\/view\/?6480826",			"link":"https:\/\/n.news.naver.com\/mnews\/article\/421\/0007350798?sid=102",			"description":"14일 대구 달서구 이월드를 찾은 시민들이 83타워 77층 전망대에 새롭게 단장한 '라라카페'에서 즐거운 시간을 보내고 있다. 이월드는 도심 전경을 한눈에 감상할 수 있는 77층 전망대에 이월드의 새로운 캐릭터... ",			"pubDate":"Wed, 14 Feb 2024 14:54:00 +0900"		},		{			"title":"‘밤에 피는 꽃’ 측 “마지막회 확대 편성 결정..5분 앞당겨 방송”[공식]",			"originallink":"http:\/\/www.osen.co.kr\/article\/G1112275067",			"link":"https:\/\/n.news.naver.com\/mnews\/article\/109\/0005017503?sid=106",			"description":"“이하늬 저세상 코믹 연기 미쳤다”, “이종원은 눈빛이 다했다”, ”빠른 전개, 웃긴 연출까지 다 갖췄음“, ”조연 배우들까지 연기 구멍이 하나도 없어서 보기 편함“, ”여기 에필로그 <b>맛집<\/b>이네“ 등 다양한... ",			"pubDate":"Wed, 14 Feb 2024 14:52:00 +0900"		}	]}

 */
import java.util.*;
@Component
public class NewsManager {
	
	/*
	 * public static void main(String[] args) { newsFind("맛집"); }
	 */
    public List<NewsVO> newsFind(String fd) {
        String clientId = "du4P_dJXInhGJKRq85PJ"; //애플리케이션 클라이언트 아이디
        String clientSecret = "bAOybXk9_K"; //애플리케이션 클라이언트 시크릿


        String text = null;
        try {
            text = URLEncoder.encode(fd, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }


        String apiURL = "https://openapi.naver.com/v1/search/news.json?query=" + text;    // JSON 결과
        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // XML 결과


        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders);
        System.out.println(responseBody);
        List<NewsVO> list=new ArrayList<NewsVO>();
        try
        {
        	JSONParser jp=new JSONParser();
        	JSONObject root=(JSONObject)jp.parse(responseBody);// {} []
        	JSONArray arr=(JSONArray)root.get("items");
        	
        	for(int i=0;i<arr.size();i++)
        	{
        		JSONObject obj=(JSONObject)arr.get(i);
        		NewsVO vo=new NewsVO();
        		vo.setTitle((String)obj.get("title"));
        		vo.setLink((String)obj.get("link"));
        		vo.setDesc((String)obj.get("description"));
        		list.add(vo);
        	}
        	
        }catch(Exception ex){}
        return list;
    }


    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 오류 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }
}
