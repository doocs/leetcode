impl Solution {
    pub fn spiral_order(matrix: Vec<Vec<i32>>) -> Vec<i32> {
        let mut ans=Vec::new();
        if matrix.len()==0{
            return ans;
        }
        let (mut left,mut right,mut top,mut bottom)=(0,matrix[0].len()-1,0,matrix.len()-1);
        loop{
            for i in left..right+1{
                ans.push(matrix[top][i]);
            }
            top+=1;
            if (top as i32)>(bottom as i32){
                break;
            }
            for i in top..bottom+1{
                ans.push(matrix[i][right]);
            }
            right-=1;
            if (right as i32)<(left as i32){
                break;
            }
            for i in (left..right+1).rev(){
                ans.push(matrix[bottom][i]);
            }
            bottom-=1;
            if (bottom as i32)<(top as i32){
                break;
            }
            for i in (top..bottom+1).rev(){
                ans.push(matrix[i][left]);
            }
            left+=1;
            if (left as i32)>(right as i32){
                break;
            }
        }
        ans
    }
  }