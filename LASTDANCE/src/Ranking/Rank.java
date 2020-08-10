package Ranking;
import User.UserDAO;
import User.UserVO;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


public class Rank {
	private static List<Double> HeightTable;
	private static Double yourHeightRank;
	private static Double totalNum;
	private static Double percentile = 100.0;
	
	public static void setHeightTable(List<Double> HT) {
		HeightTable = HT;
	}
	public List<Double> getHeightTable(){
		return HeightTable;
	}
	public static double getPercentile() {
		return percentile;
	}
	public static double[] Return_Rank(UserVO uv, UserDAO ud) {
		
		//List<Double> HeightTable = new ArrayList<Double>();
		List<Object> IdTable = new ArrayList<Object>();
		double[] Results = new double[3];
		
		ud.Insert_DB(uv);
		//ud.Insert_User(uv); 추후 넣을 기
		//HeightTable = ud.Return_Height(uv);
		setHeightTable(ud.Return_Height(uv));
		
		totalNum = (double) HeightTable.size();
		
		int yourId = ud.Find_STONE(uv);
	
		IdTable = ud.Return_Id(uv);
		
		yourHeightRank = (double) (IdTable.indexOf(yourId) + 1);
		
		percentile = (double)yourHeightRank / (double)totalNum * 100;
		
		System.out.println("Your Rank is " + yourHeightRank + "(total : " + totalNum + ")");
		
		System.out.println("percentile : " + percentile + "%");
		
		ud.Delete_DB(yourId, uv);
		
		if(yourId == -1) {
			System.out.println("ERROR: Can't Find User's data.");
		}
		
		Results[0] = yourHeightRank;
		Results[1] = percentile;
		Results[2] = totalNum;
	
		return Results;
	}
	
	public static int Return_Mean(UserVO uv, UserDAO ud) {
		int MeanHeight = 0;
		double total = 0;
		
		setHeightTable(ud.Return_Height_ASC(uv));
		for (int i = 0; i< HeightTable.size(); i++) {
			total += (HeightTable.get(i));
			
		}
		MeanHeight = (int)total/HeightTable.size();
		return MeanHeight;
	}
	
	public static TreeMap<Integer, Integer> Return_norm(UserVO uv, UserDAO ud){
		TreeMap<Integer, Integer> ResultNorm = new TreeMap<>();
		ResultNorm.clear();
		
		int MeanHeight = Return_Mean(uv, ud);
		
		int level_value = 20;
		
		int[] levels = new int[level_value];
		int[] frequency = new int[level_value];
		for(int i = 0; i < level_value; i++) {
			if ( i == 0) levels[i] =MeanHeight - 20;
			else {
				levels[i]= levels[i-1] + 2;
			}
		}
		int count = 0;
		for(int j = 0; j < level_value; j++) {
			for(int i = 0; i< HeightTable.size(); i++) {
				if (j == 0) {
					if (HeightTable.get(i) <= levels[j]) count++;
					else break;
				}
				else {
					if (HeightTable.get(i) <= levels[j] && HeightTable.get(i) >= levels[j-1]) count++;
				}
			}
			frequency[j] = count;
			count = 0;
		}
		for(int i = 0; i< level_value; i++) {
			ResultNorm.put(levels[i], frequency[i]);
		}
		System.out.println("System return Norms");
		return ResultNorm;
	}
 	
	public static String cutDecimal(int cutSize, double value) {
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(cutSize);
		nf.setGroupingUsed(false);
		
		return nf.format(value);
	}
	

	public static void main(String[] args) {
		UserVO user = new UserVO(0, 1, 17, 174.2, 1);
		UserDAO dao = new UserDAO();
		
		
		Return_Rank(user, dao);
		
	}
}
