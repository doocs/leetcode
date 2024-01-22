# [3013. Divide an Array Into Subarrays With Minimum Cost II](https://leetcode.com/problems/divide-an-array-into-subarrays-with-minimum-cost-ii)

[中文文档](/solution/3000-3099/3013.Divide%20an%20Array%20Into%20Subarrays%20With%20Minimum%20Cost%20II/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> array of integers <code>nums</code> of length <code>n</code>, and two <strong>positive</strong> integers <code>k</code> and <code>dist</code>.</p>

<p>The <strong>cost</strong> of an array is the value of its <strong>first</strong> element. For example, the cost of <code>[1,2,3]</code> is <code>1</code> while the cost of <code>[3,4,1]</code> is <code>3</code>.</p>

<p>You need to divide <code>nums</code> into <code>k</code> <strong>disjoint contiguous </strong><span data-keyword="subarray-nonempty">subarrays</span>, such that the difference between the starting index of the <strong>second</strong> subarray and the starting index of the <code>kth</code> subarray should be <strong>less than or equal to</strong> <code>dist</code>. In other words, if you divide <code>nums</code> into the subarrays <code>nums[0..(i<sub>1</sub> - 1)], nums[i<sub>1</sub>..(i<sub>2</sub> - 1)], ..., nums[i<sub>k-1</sub>..(n - 1)]</code>, then <code>i<sub>k-1</sub> - i<sub>1</sub> &lt;= dist</code>.</p>

<p>Return <em>the <strong>minimum</strong> possible sum of the cost of these</em> <em>subarrays</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,2,6,4,2], k = 3, dist = 3
<strong>Output:</strong> 5
<strong>Explanation:</strong> The best possible way to divide nums into 3 subarrays is: [1,3], [2,6,4], and [2]. This choice is valid because i<sub>k-1</sub> - i<sub>1</sub> is 5 - 2 = 3 which is equal to dist. The total cost is nums[0] + nums[2] + nums[5] which is 1 + 2 + 2 = 5.
It can be shown that there is no possible way to divide nums into 3 subarrays at a cost lower than 5.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,1,2,2,2,1], k = 4, dist = 3
<strong>Output:</strong> 15
<strong>Explanation:</strong> The best possible way to divide nums into 4 subarrays is: [10], [1], [2], and [2,2,1]. This choice is valid because i<sub>k-1</sub> - i<sub>1</sub> is 3 - 1 = 2 which is less than dist. The total cost is nums[0] + nums[1] + nums[2] + nums[3] which is 10 + 1 + 2 + 2 = 15.
The division [10], [1], [2,2,2], and [1] is not valid, because the difference between i<sub>k-1</sub> and i<sub>1</sub> is 5 - 1 = 4, which is greater than dist.
It can be shown that there is no possible way to divide nums into 4 subarrays at a cost lower than 15.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,8,18,9], k = 3, dist = 1
<strong>Output:</strong> 36
<strong>Explanation:</strong> The best possible way to divide nums into 4 subarrays is: [10], [8], and [18,9]. This choice is valid because i<sub>k-1</sub> - i<sub>1</sub> is 2 - 1 = 1 which is equal to dist.The total cost is nums[0] + nums[1] + nums[2] which is 10 + 8 + 18 = 36.
The division [10], [8,18], and [9] is not valid, because the difference between i<sub>k-1</sub> and i<sub>1</sub> is 3 - 1 = 2, which is greater than dist.
It can be shown that there is no possible way to divide nums into 3 subarrays at a cost lower than 36.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>3 &lt;= k &lt;= n</code></li>
	<li><code>k - 2 &lt;= dist &lt;= n - 2</code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python

```

```java

```

```cpp
class Solution {
public:
    long long minimumCost(vector<int>& nums, int k, int dist) {
        multiset<int> sml, big;
        int sz = dist + 1;
        long long sum = 0, ans = 0;
        for(int i = 1; i <= sz; i++){
            sml.insert(nums[i]);
            sum += nums[i];
        }
        while(sml.size() > k-1){
            big.insert(*sml.rbegin());
            sum -= *sml.rbegin();
            sml.erase(sml.find(*sml.rbegin()));
        }
        ans = sum;
        for(int i = sz+1; i < nums.size(); i++){
            sum += nums[i];
            sml.insert(nums[i]);
            if(big.find(nums[i - sz]) != big.end()){
                big.erase(big.find(nums[i - sz]));
            }
            else{
                sum -= nums[i - sz];
                sml.erase(sml.find(nums[i - sz]));
            }
            
            while(sml.size() > k-1){
                sum -= *sml.rbegin();
                big.insert(*sml.rbegin());
                sml.erase(sml.find(*sml.rbegin()));
            }
            while(sml.size() < k-1){
                sum += *big.begin();
                sml.insert(*big.begin());
                big.erase(big.begin());
            }
            while(!sml.empty() && !big.empty() && *sml.rbegin() > *big.begin()){
                sum -= *sml.rbegin() - *big.begin();
                sml.insert(*big.begin());
                big.insert(*sml.rbegin());
                sml.erase(sml.find(*sml.rbegin()));
                big.erase(big.begin());
            }
            ans = min(ans, sum);
        }
        int p = 0;
        return nums[0] + ans;
    }
};
```

```go

```

<!-- tabs:end -->

<!-- end -->
