# [2449. Minimum Number of Operations to Make Arrays Similar](https://leetcode.com/problems/minimum-number-of-operations-to-make-arrays-similar)

[中文文档](/solution/2400-2499/2449.Minimum%20Number%20of%20Operations%20to%20Make%20Arrays%20Similar/README.md)

## Description

<p>You are given two positive integer arrays <code>nums</code> and <code>target</code>, of the same length.</p>

<p>In one operation, you can choose any two <strong>distinct</strong> indices <code>i</code> and <code>j</code> where <code>0 &lt;= i, j &lt; nums.length</code> and:</p>

<ul>
	<li>set <code>nums[i] = nums[i] + 2</code> and</li>
	<li>set <code>nums[j] = nums[j] - 2</code>.</li>
</ul>

<p>Two arrays are considered to be <strong>similar</strong> if the frequency of each element is the same.</p>

<p>Return <em>the minimum number of operations required to make </em><code>nums</code><em> similar to </em><code>target</code>. The test cases are generated such that <code>nums</code> can always be similar to <code>target</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [8,12,6], target = [2,14,10]
<strong>Output:</strong> 2
<strong>Explanation:</strong> It is possible to make nums similar to target in two operations:
- Choose i = 0 and j = 2, nums = [10,12,4].
- Choose i = 1 and j = 2, nums = [10,14,2].
It can be shown that 2 is the minimum number of operations needed.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,5], target = [4,1,3]
<strong>Output:</strong> 1
<strong>Explanation:</strong> We can make nums similar to target in one operation:
- Choose i = 1 and j = 2, nums = [1,4,3].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,1,1], target = [1,1,1,1,1]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The array nums is already similiar to target.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length == target.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], target[i] &lt;= 10<sup>6</sup></code></li>
	<li>It is possible to make <code>nums</code> similar to <code>target</code>.</li>
</ul>

## Solutions

### Solution 1: Odd-Even Classification + Sorting

Notice that, because each operation will only increase or decrease the value of an element by $2$, the parity of the element will not change.

Therefore, we can divide the arrays $nums$ and $target$ into two groups according to their parity, denoted as $a_1$ and $a_2$, and $b_1$ and $b_2$ respectively.

Then, we just need to pair the elements in $a_1$ with the elements in $b_1$, and pair the elements in $a_2$ with the elements in $b_2$, and then perform operations. During the pairing process, we can use a greedy strategy, pairing the smaller elements in $a_i$ with the smaller elements in $b_i$ each time, which can ensure the minimum number of operations. This can be directly implemented through sorting.

Since each operation can reduce the difference of the corresponding elements by $4$, we accumulate the difference of each corresponding position, and finally divide by $4$ to get the answer.

The time complexity is $O(n \times \log n)$, where $n$ is the length of the array $nums$.

<!-- tabs:start -->

```python
class Solution:
    def makeSimilar(self, nums: List[int], target: List[int]) -> int:
        nums.sort(key=lambda x: (x & 1, x))
        target.sort(key=lambda x: (x & 1, x))
        return sum(abs(a - b) for a, b in zip(nums, target)) // 4
```

```java
class Solution {
    public long makeSimilar(int[] nums, int[] target) {
        Arrays.sort(nums);
        Arrays.sort(target);
        List<Integer> a1 = new ArrayList<>();
        List<Integer> a2 = new ArrayList<>();
        List<Integer> b1 = new ArrayList<>();
        List<Integer> b2 = new ArrayList<>();
        for (int v : nums) {
            if (v % 2 == 0) {
                a1.add(v);
            } else {
                a2.add(v);
            }
        }
        for (int v : target) {
            if (v % 2 == 0) {
                b1.add(v);
            } else {
                b2.add(v);
            }
        }
        long ans = 0;
        for (int i = 0; i < a1.size(); ++i) {
            ans += Math.abs(a1.get(i) - b1.get(i));
        }
        for (int i = 0; i < a2.size(); ++i) {
            ans += Math.abs(a2.get(i) - b2.get(i));
        }
        return ans / 4;
    }
}
```

```cpp
class Solution {
public:
    long long makeSimilar(vector<int>& nums, vector<int>& target) {
        sort(nums.begin(), nums.end());
        sort(target.begin(), target.end());
        vector<int> a1;
        vector<int> a2;
        vector<int> b1;
        vector<int> b2;
        for (int v : nums) {
            if (v & 1)
                a1.emplace_back(v);
            else
                a2.emplace_back(v);
        }
        for (int v : target) {
            if (v & 1)
                b1.emplace_back(v);
            else
                b2.emplace_back(v);
        }
        long long ans = 0;
        for (int i = 0; i < a1.size(); ++i) ans += abs(a1[i] - b1[i]);
        for (int i = 0; i < a2.size(); ++i) ans += abs(a2[i] - b2[i]);
        return ans / 4;
    }
};
```

```go
func makeSimilar(nums []int, target []int) int64 {
	sort.Ints(nums)
	sort.Ints(target)
	a1, a2, b1, b2 := []int{}, []int{}, []int{}, []int{}
	for _, v := range nums {
		if v%2 == 0 {
			a1 = append(a1, v)
		} else {
			a2 = append(a2, v)
		}
	}
	for _, v := range target {
		if v%2 == 0 {
			b1 = append(b1, v)
		} else {
			b2 = append(b2, v)
		}
	}
	ans := 0
	for i := 0; i < len(a1); i++ {
		ans += abs(a1[i] - b1[i])
	}
	for i := 0; i < len(a2); i++ {
		ans += abs(a2[i] - b2[i])
	}
	return int64(ans / 4)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

```ts
function makeSimilar(nums: number[], target: number[]): number {
    nums.sort((a, b) => a - b);
    target.sort((a, b) => a - b);

    const a1: number[] = [];
    const a2: number[] = [];
    const b1: number[] = [];
    const b2: number[] = [];

    for (const v of nums) {
        if (v % 2 === 0) {
            a1.push(v);
        } else {
            a2.push(v);
        }
    }

    for (const v of target) {
        if (v % 2 === 0) {
            b1.push(v);
        } else {
            b2.push(v);
        }
    }

    let ans = 0;
    for (let i = 0; i < a1.length; ++i) {
        ans += Math.abs(a1[i] - b1[i]);
    }

    for (let i = 0; i < a2.length; ++i) {
        ans += Math.abs(a2[i] - b2[i]);
    }

    return ans / 4;
}
```

<!-- tabs:end -->

<!-- end -->
