# [2191. Sort the Jumbled Numbers](https://leetcode.com/problems/sort-the-jumbled-numbers)

[中文文档](/solution/2100-2199/2191.Sort%20the%20Jumbled%20Numbers/README.md)

<!-- tags:Array,Sorting -->

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>mapping</code> which represents the mapping rule of a shuffled decimal system. <code>mapping[i] = j</code> means digit <code>i</code> should be mapped to digit <code>j</code> in this system.</p>

<p>The <strong>mapped value</strong> of an integer is the new integer obtained by replacing each occurrence of digit <code>i</code> in the integer with <code>mapping[i]</code> for all <code>0 &lt;= i &lt;= 9</code>.</p>

<p>You are also given another integer array <code>nums</code>. Return <em>the array </em><code>nums</code><em> sorted in <strong>non-decreasing</strong> order based on the <strong>mapped values</strong> of its elements.</em></p>

<p><strong>Notes:</strong></p>

<ul>
	<li>Elements with the same mapped values should appear in the <strong>same relative order</strong> as in the input.</li>
	<li>The elements of <code>nums</code> should only be sorted based on their mapped values and <strong>not be replaced</strong> by them.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> mapping = [8,9,4,0,2,1,3,5,7,6], nums = [991,338,38]
<strong>Output:</strong> [338,38,991]
<strong>Explanation:</strong> 
Map the number 991 as follows:
1. mapping[9] = 6, so all occurrences of the digit 9 will become 6.
2. mapping[1] = 9, so all occurrences of the digit 1 will become 9.
Therefore, the mapped value of 991 is 669.
338 maps to 007, or 7 after removing the leading zeros.
38 maps to 07, which is also 7 after removing leading zeros.
Since 338 and 38 share the same mapped value, they should remain in the same relative order, so 338 comes before 38.
Thus, the sorted array is [338,38,991].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> mapping = [0,1,2,3,4,5,6,7,8,9], nums = [789,456,123]
<strong>Output:</strong> [123,456,789]
<strong>Explanation:</strong> 789 maps to 789, 456 maps to 456, and 123 maps to 123. Thus, the sorted array is [123,456,789].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>mapping.length == 10</code></li>
	<li><code>0 &lt;= mapping[i] &lt;= 9</code></li>
	<li>All the values of <code>mapping[i]</code> are <strong>unique</strong>.</li>
	<li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt; 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1: Custom Sorting

We traverse each element $nums[i]$ in the array $nums$, store its mapped value $y$ and index $i$ into the array $arr$, then sort the array $arr$. Finally, we extract the index $i$ from the sorted array $arr$, convert it to the element $nums[i]$ in the original array $nums$.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Where $n$ is the length of the array $nums$.

<!-- tabs:start -->

```python
class Solution:
    def sortJumbled(self, mapping: List[int], nums: List[int]) -> List[int]:
        def f(x: int) -> int:
            if x == 0:
                return mapping[0]
            y, k = 0, 1
            while x:
                x, v = divmod(x, 10)
                v = mapping[v]
                y = k * v + y
                k *= 10
            return y

        arr = sorted((f(x), i) for i, x in enumerate(nums))
        return [nums[i] for _, i in arr]
```

```java
class Solution {
    private int[] mapping;

    public int[] sortJumbled(int[] mapping, int[] nums) {
        this.mapping = mapping;
        int n = nums.length;
        int[][] arr = new int[n][0];
        for (int i = 0; i < n; ++i) {
            arr[i] = new int[] {f(nums[i]), i};
        }
        Arrays.sort(arr, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = nums[arr[i][1]];
        }
        return ans;
    }

    private int f(int x) {
        if (x == 0) {
            return mapping[0];
        }
        int y = 0;
        for (int k = 1; x > 0; x /= 10) {
            int v = mapping[x % 10];
            y = k * v + y;
            k *= 10;
        }
        return y;
    }
}
```

```cpp
class Solution {
public:
    vector<int> sortJumbled(vector<int>& mapping, vector<int>& nums) {
        auto f = [&](int x) {
            if (x == 0) {
                return mapping[0];
            }
            int y = 0;
            for (int k = 1; x; x /= 10) {
                int v = mapping[x % 10];
                y = k * v + y;
                k *= 10;
            }
            return y;
        };
        const int n = nums.size();
        vector<pair<int, int>> arr;
        for (int i = 0; i < n; ++i) {
            arr.emplace_back(f(nums[i]), i);
        }
        sort(arr.begin(), arr.end());
        vector<int> ans;
        for (const auto& [_, i] : arr) {
            ans.push_back(nums[i]);
        }
        return ans;
    }
};
```

```go
func sortJumbled(mapping []int, nums []int) (ans []int) {
	n := len(nums)
	f := func(x int) (y int) {
		if x == 0 {
			return mapping[0]
		}
		for k := 1; x > 0; x /= 10 {
			v := mapping[x%10]
			y = k*v + y
			k *= 10
		}
		return
	}
	arr := make([][2]int, n)
	for i, x := range nums {
		arr[i] = [2]int{f(x), i}
	}
	sort.Slice(arr, func(i, j int) bool { return arr[i][0] < arr[j][0] || arr[i][0] == arr[j][0] && arr[i][1] < arr[j][1] })
	for _, p := range arr {
		ans = append(ans, nums[p[1]])
	}
	return
}
```

```ts
function sortJumbled(mapping: number[], nums: number[]): number[] {
    const n = nums.length;
    const f = (x: number): number => {
        if (x === 0) {
            return mapping[0];
        }
        let y = 0;
        for (let k = 1; x; x = (x / 10) | 0) {
            const v = mapping[x % 10];
            y += v * k;
            k *= 10;
        }
        return y;
    };
    const arr: number[][] = nums.map((x, i) => [f(x), i]);
    arr.sort((a, b) => (a[0] === b[0] ? a[1] - b[1] : a[0] - b[0]));
    return arr.map(x => nums[x[1]]);
}
```

```rust
impl Solution {
    pub fn sort_jumbled(mapping: Vec<i32>, nums: Vec<i32>) -> Vec<i32> {
        let f = |x: i32| -> i32 {
            if x == 0 {
                return mapping[0];
            }
            let mut y = 0;
            let mut k = 1;
            let mut num = x;
            while num != 0 {
                let v = mapping[(num % 10) as usize];
                y = k * v + y;
                k *= 10;
                num /= 10;
            }
            y
        };

        let n = nums.len();
        let mut arr: Vec<(i32, usize)> = Vec::with_capacity(n);
        for i in 0..n {
            arr.push((f(nums[i]), i));
        }
        arr.sort();

        let mut ans: Vec<i32> = Vec::with_capacity(n);
        for (_, i) in arr {
            ans.push(nums[i]);
        }
        ans
    }
}
```

```cs
public class Solution {
    public int[] SortJumbled(int[] mapping, int[] nums) {
        Func<int, int> f = (int x) => {
            if (x == 0) {
                return mapping[0];
            }
            int y = 0;
            int k = 1;
            int num = x;
            while (num != 0) {
                int v = mapping[num % 10];
                y = k * v + y;
                k *= 10;
                num /= 10;
            }
            return y;
        };

        int n = nums.Length;
        List<(int, int)> arr = new List<(int, int)>();
        for (int i = 0; i < n; ++i) {
            arr.Add((f(nums[i]), i));
        }
        arr.Sort();

        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = nums[arr[i].Item2];
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- end -->
