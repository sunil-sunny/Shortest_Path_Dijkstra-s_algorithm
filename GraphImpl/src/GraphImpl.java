




public class GraphImpl{
	
public static void main(String args[]) {
	HalifaxMap hMap = new HalifaxMap();
    
	hMap.newIntersection(5, 4);//0
	hMap.newIntersection(14, 6);//1
	hMap.newIntersection(3, 1);//2
	hMap.newIntersection(7, 9);//3
	hMap.newIntersection(6, 4);//4
	hMap.defineRoad(5, 4, 14,6);//0 1
	hMap.defineRoad(5, 4, 3,1);//0 2
	hMap.defineRoad(3,1, 14, 6);//2 1 
	hMap.defineRoad(3,1, 6, 4);//2 4
	hMap.defineRoad(6,4, 7, 9);//4 3
	//hMap.defineRoad( 7, 9,14,6);	
	hMap.defineRoad(3,1, 14, 6);//2 1 
	hMap.defineRoad(14,6, 5, 4);
	hMap.navigate(7,9,14,6);
}
}
