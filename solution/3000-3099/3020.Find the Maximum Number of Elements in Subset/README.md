# [3020. 子集中元素的最大数量](https://leetcode.cn/problems/find-the-maximum-number-of-elements-in-subset)

[English Version](/solution/3000-3099/3020.Find%20the%20Maximum%20Number%20of%20Elements%20in%20Subset/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个<strong> 正整数 </strong>数组 <code>nums</code> 。</p>

<p>你需要从数组中选出一个满足下述条件的<span data-keyword="subset">子集</span>：</p>

<ul>
	<li>你可以将选中的元素放置在一个下标从 <strong>0</strong> 开始的数组中，并使其遵循以下模式：<code>[x, x<sup>2</sup>, x<sup>4</sup>, ..., x<sup>k/2</sup>, x<sup>k</sup>, x<sup>k/2</sup>, ..., x<sup>4</sup>, x<sup>2</sup>, x]</code>（<strong>注意</strong>，<code>k</code> 可以是任何 <strong>非负</strong> 的 2 的幂）。例如，<code>[2, 4, 16, 4, 2]</code> 和 <code>[3, 9, 3]</code> 都符合这一模式，而 <code>[2, 4, 8, 4, 2]</code> 则不符合。</li>
</ul>

<p>返回满足这些条件的子集中，元素数量的 <strong>最大值 </strong><em>。</em></p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [5,4,1,2,2]
<strong>输出：</strong>3
<strong>解释：</strong>选择子集 {4,2,2} ，将其放在数组 [2,4,2] 中，它遵循该模式，且 2<sup>2</sup> == 4 。因此答案是 3 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,3,2,4]
<strong>输出：</strong>1
<strong>解释：</strong>选择子集 {1}，将其放在数组 [1] 中，它遵循该模式。因此答案是 1 。注意我们也可以选择子集 {2} 、{4} 或 {3} ，可能存在多个子集都能得到相同的答案。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def maximumLength(self, nums: List[int]) -> int:
        d = {}
        for num in sorted(nums)[::-1]:
            if num**2 in d and num in d and num != 1:
                d[num] = d[num**2] + 2
            else:
                d[num] = 1
        ones = nums.count(1)
        return max(max(d.values()), ones - (ones % 2 == 0))

```

```java
class Solution {
    public int maximumLength(int[] nums) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        int max = 0;
       
        for (Map.Entry<Integer, Integer> i : map.entrySet()) {
            System.out.println(i.getValue());
            if (i.getValue() >= 2 && i.getKey() != 1) {
                int x = i.getKey();
                int c = 2;
                while (map.containsKey(x * x)) {
                    if (map.get(x * x) == 1) {
                        max = Math.max(max, c + 1);
                        break;
                    } else if (map.get(x * x) >= 2) {
                        max = Math.max(max, c + 1);
                        x = x * x;
                    }
                    c += 2;
                }
            }
        }
        if (map.containsKey(1) && map.get(1) - 1 > max) {
            return (map.get(1) % 2 != 0) ? map.get(1) : map.get(1) - 1;
        }
        return max == 0 ? 1 : max;
    }
}
```

```cpp
class Solution {
public:
    int maximumLength(vector<int>& nums) {
        long long ans = 0;
        map<int, int> freq;
        for (auto n : nums) {
            freq[n]++;
        }
        for (auto [k, f] : freq) {
            long long t = k, count = 0;
            if (t == 1) {
                count += freq[t];
                freq[t] = 0;
            }
            while (t < INT_MAX && freq[t] > 0) {
                count += 2;
                if (freq[t] == 1) {
                    break;
                }
                freq[t] = 0;
                t = t * t;
            }
            if (count % 2 == 0) {
                count--;
            }
            ans = max(ans, count);
        }
        return ans;
    }
};
```

```go
func minExp(x, c int) (int, int){
    d := math.Sqrt(float64(x))
    if d<2 || float64(int(d)) < d{
        return x, c
    }
    return minExp(int(d), c+1)
}


func maximumLength(nums []int) int {
    m := make(map[int][]int)
    for i := range nums{
        base, c := minExp(nums[i], 1)
        m[base] = append(m[base], c)
    }
    max := 1
    for _, v := range m{
        v := matchPattern(v)
        max = Max(max, v)
    }
    _, ok := m[1]; if ok{
        if len(m[1]) %2 > 0{
            max = Max(max, len(m[1]))
        }else{
            max = Max(max, len(m[1])-1)
        }
    }
    return max
}

func Max(i,j int) int{
    if i>j{return i}
    return j
}

func matchPattern(arr []int) int{
    sort.Slice(arr, func(i,j int)bool{return arr[i] < arr[j]})
    start := arr[0]
    bin := 2
    for i := range arr{
        if bin == 0{
            start++
            bin = 2
        }
        if arr[i] == start{
            bin--
        }
    }
    if bin == 1 {
        return 2*(start-arr[0])+1
    } else if bin == 0{
        return 2*(start-arr[0])+1
    } else {
        return 2*(start-arr[0])-1
    }
}
```

<!-- tabs:end -->

<!-- end -->
