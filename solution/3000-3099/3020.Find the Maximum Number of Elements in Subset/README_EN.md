# [3020. Find the Maximum Number of Elements in Subset](https://leetcode.com/problems/find-the-maximum-number-of-elements-in-subset)

[中文文档](/solution/3000-3099/3020.Find%20the%20Maximum%20Number%20of%20Elements%20in%20Subset/README.md)

## Description

<p>You are given an array of <strong>positive</strong> integers <code>nums</code>.</p>

<p>You need to select a <span data-keyword="subset">subset</span> of <code>nums</code> which satisfies the following condition:</p>

<ul>
	<li>You can place the selected elements in a <strong>0-indexed</strong> array such that it follows the pattern: <code>[x, x<sup>2</sup>, x<sup>4</sup>, ..., x<sup>k/2</sup>, x<sup>k</sup>, x<sup>k/2</sup>, ..., x<sup>4</sup>, x<sup>2</sup>, x]</code> (<strong>Note</strong> that <code>k</code> can be be any <strong>non-negative</strong> power of <code>2</code>). For example, <code>[2, 4, 16, 4, 2]</code> and <code>[3, 9, 3]</code> follow the pattern while <code>[2, 4, 8, 4, 2]</code> does not.</li>
</ul>

<p>Return <em>the <strong>maximum</strong> number of elements in a subset that satisfies these conditions.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,4,1,2,2]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can select the subset {4,2,2}, which can be placed in the array as [2,4,2] which follows the pattern and 2<sup>2</sup> == 4. Hence the answer is 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,2,4]
<strong>Output:</strong> 1
<strong>Explanation:</strong> We can select the subset {1}, which can be placed in the array as [1] which follows the pattern. Hence the answer is 1. Note that we could have also selected the subsets {2}, {4}, or {3}, there may be multiple subsets which provide the same answer. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1

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
