# [3013. 将数组分成最小总代价的子数组 II](https://leetcode.cn/problems/divide-an-array-into-subarrays-with-minimum-cost-ii)

[English Version](/solution/3000-3099/3013.Divide%20an%20Array%20Into%20Subarrays%20With%20Minimum%20Cost%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;和两个 <strong>正</strong>&nbsp;整数&nbsp;<code>k</code> 和&nbsp;<code>dist</code>&nbsp;。</p>

<p>一个数组的 <strong>代价</strong>&nbsp;是数组中的 <strong>第一个</strong>&nbsp;元素。比方说，<code>[1,2,3]</code>&nbsp;的代价为&nbsp;<code>1</code>&nbsp;，<code>[3,4,1]</code>&nbsp;的代价为&nbsp;<code>3</code>&nbsp;。</p>

<p>你需要将 <code>nums</code>&nbsp;分割成 <code>k</code>&nbsp;个 <strong>连续且互不相交</strong>&nbsp;的子数组，满足 <strong>第二</strong>&nbsp;个子数组与第 <code>k</code>&nbsp;个子数组中第一个元素的下标距离 <strong>不超过</strong>&nbsp;<code>dist</code>&nbsp;。换句话说，如果你将&nbsp;<code>nums</code>&nbsp;分割成子数组&nbsp;<code>nums[0..(i<sub>1</sub> - 1)], nums[i<sub>1</sub>..(i<sub>2</sub> - 1)], ..., nums[i<sub>k-1</sub>..(n - 1)]</code>&nbsp;，那么它需要满足&nbsp;<code>i<sub>k-1</sub> - i<sub>1</sub> &lt;= dist</code>&nbsp;。</p>

<p>请你返回这些子数组的 <strong>最小</strong>&nbsp;总代价。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,3,2,6,4,2], k = 3, dist = 3
<b>输出：</b>5
<b>解释：</b>将数组分割成 3 个子数组的最优方案是：[1,3] ，[2,6,4] 和 [2] 。这是一个合法分割，因为 i<sub>k-1</sub> - i<sub>1</sub> 等于 5 - 2 = 3 ，等于 dist 。总代价为 nums[0] + nums[2] + nums[5] ，也就是 1 + 2 + 2 = 5 。
5 是分割成 3 个子数组的最小总代价。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [10,1,2,2,2,1], k = 4, dist = 3
<b>输出：</b>15
<b>解释：</b>将数组分割成 4 个子数组的最优方案是：[10] ，[1] ，[2] 和 [2,2,1] 。这是一个合法分割，因为 i<sub>k-1</sub> - i<sub>1</sub> 等于 3 - 1 = 2 ，小于 dist 。总代价为 nums[0] + nums[1] + nums[2] + nums[3] ，也就是 10 + 1 + 2 + 2 = 15 。
分割 [10] ，[1] ，[2,2,2] 和 [1] 不是一个合法分割，因为 i<sub>k-1</sub> 和 i<sub>1</sub> 的差为 5 - 1 = 4 ，大于 dist 。
15 是分割成 4 个子数组的最小总代价。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [10,8,18,9], k = 3, dist = 1
<b>输出：</b>36
<b>解释：</b>将数组分割成 4 个子数组的最优方案是：[10] ，[8] 和 [18,9] 。这是一个合法分割，因为 i<sub>k-1</sub> - i<sub>1</sub> 等于 2 - 1 = 1 ，等于 dist 。总代价为 nums[0] + nums[1] + nums[2] ，也就是 10 + 8 + 18 = 36 。
分割 [10] ，[8,18] 和 [9] 不是一个合法分割，因为 i<sub>k-1</sub> 和 i<sub>1</sub> 的差为 3 - 1 = 2 ，大于 dist 。
36 是分割成 3 个子数组的最小总代价。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>3 &lt;= k &lt;= n</code></li>
	<li><code>k - 2 &lt;= dist &lt;= n - 2</code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python
from sortedcontainers import SortedList
class Solution:
    def minimumCost(self, nums: List[int], k: int, dist: int) -> int:
        n=len(nums)

        sl = SortedList()
        y = nums[0]
        ans = float("inf")
        i = 1
        running_sum = 0

        for j in range(1, n):
            pos = bisect.bisect_left(sl, nums[j])
            sl.add(nums[j])
            
            if pos < k - 1:
                running_sum += nums[j]
                if len(sl) > k - 1:
                    running_sum -= sl[k-1]

            while j - i > dist:
                removed_pos = sl.index(nums[i])
                removed_element = nums[i]
                sl.remove(removed_element)
                
                if removed_pos < k - 1:
                    running_sum -= removed_element
                    if len(sl) >= k - 1:
                        running_sum += sl[k-2]
                i += 1

            if j - i + 1 >= k - 1:
                ans = min(ans, running_sum)

        return ans + y
```

```java
class Solution {
    public long minimumCost(int[] nums, int k, int dist) {
        long result = Long.MAX_VALUE, sum = 0l;
        int n = nums.length;
        TreeSet<Integer> set1 = new TreeSet<>((a, b) -> nums[a] == nums[b] ? a - b : nums[a] - nums[b]);
        TreeSet<Integer> set2 = new TreeSet<>((a, b) -> nums[a] == nums[b] ? a - b : nums[a] - nums[b]);
        for(int i=1;i<n;i++)
        {
            set1.add(i);
            sum+=nums[i];
            if(set1.size()>=k)
            {
                int x=set1.pollLast();
                sum-=nums[x];
                set2.add(x);
            }
            if(i-dist>0)
            {
                result=Math.min(result,sum);
                int temp=i-dist;
                if(set1.contains(temp))
                {
                    set1.remove(temp);
                    sum-=nums[temp];
                    if(set2.size()>0)
                    {
                        int y=set2.pollFirst();
                        sum+=nums[y];
                        set1.add(y);
                    }
                }
                else{
                    set2.remove(i-dist);
                }
            }
        }
        return result+nums[0];
    }
}
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
