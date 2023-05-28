import java.util.*;

class Solution {
    public long solution(int n) {
        ArrayList<Integer> tmp = new ArrayList<Integer>();//큐 처럼 사용
        ArrayList<Integer> list = new ArrayList<Integer>();//불길한 수 수열
        int cnt=0;
        tmp.add(4);
        tmp.add(13);
        
        while(true) {
            if(cnt > n) {
                break;
            }
            int num = tmp.remove(0);
            tmp.add(Integer.parseInt(num+"4"));
            tmp.add(Integer.parseInt(num+"13"));
            list.add(num);
            cnt++;
        }
        Collections.sort(list);//정렬
        return list.get(n-1);
    }
}
