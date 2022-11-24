
 //* @param {number[]} nums
 //* @return {number[][]}
 
var subsets = function(nums) {
    let result=[]
          
  const dfs=(i,nums,slate)=>{
      
    if(i===nums.length){
      result.push(slate.slice())
      return
    }
      
      dfs(i+1,nums,slate)
      slate.push(nums[i])
      dfs(i+1,nums,slate)
      slate.pop()
  }
  dfs(0,nums,[])
  return result
};
