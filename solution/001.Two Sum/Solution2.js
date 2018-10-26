var twoSum = function(nums, target) {
    var len = nums.length;
    var n = {};
    for(var i = 0; i < len; i++){
        if(n[target - nums[i]] !== undefined){
            return [n[target - nums[i]], i];
        }
        n[nums[i]] = i;
    }
};
