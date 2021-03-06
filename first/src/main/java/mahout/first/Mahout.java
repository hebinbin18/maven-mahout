package mahout.first;

import org.apache.mahout.cf.taste.impl.model.file.*;
import org.apache.mahout.cf.taste.impl.neighborhood.*;
import org.apache.mahout.cf.taste.impl.recommender.*;
import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.model.*;
import org.apache.mahout.cf.taste.neighborhood.*;
import org.apache.mahout.cf.taste.recommender.*;
import org.apache.mahout.cf.taste.similarity.*;
import java.io.*;
import java.util.*;
public class Mahout {
	private Mahout(){};
	
	public static void main (String args[])throws Exception{
		System.out.println("hello world");
		
//		step:1 构建模型 2 计算相似度 3 查找k紧邻 4 构造推荐引擎
		DataModel  model = new FileDataModel(new File("data.csv"));
		UserSimilarity similarity =new PearsonCorrelationSimilarity(model);
		UserNeighborhood neighborhood =new NearestNUserNeighborhood(2,similarity,model);
		Recommender recommender= new GenericUserBasedRecommender(model,neighborhood,similarity);
		List<RecommendedItem> recommendations =recommender.recommend(1, 2);
		for(RecommendedItem recommendation :recommendations){
			System.out.println(recommendation);
		}
		
	}
}
