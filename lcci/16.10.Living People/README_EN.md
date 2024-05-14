---
comment: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/16.10.Living%20People/README_EN.md
---

# [16.10. Living People](https://leetcode.cn/problems/living-people-lcci)

[中文文档](/lcci/16.10.Living%20People/README.md)

## Description

<p>Given a list of people with their birth and death years, implement a method to compute the year with the most number of people alive. You may assume that all people were born between 1900 and 2000 (inclusive). If a person was alive during any portion of that year, they should be included in that year&#39;s count. For example, Person (birth= 1908, death= 1909) is included in the counts for both 1908 and 1909.</p>

<p>If there are more than one years&nbsp;that have the most number of people alive, return the smallest one.</p>

<p><strong>Example: </strong></p>

<pre>

<strong>Input: </strong>

birth = {1900, 1901, 1950}

death = {1948, 1951, 2000}

<strong>Output: </strong> 1901

</pre>

<p><strong>Note: </strong></p>

<ul>
	<li><code>0 &lt; birth.length == death.length &lt;= 10000</code></li>
	<li><code>birth[i] &lt;= death[i]</code></li>
</ul>

## Solutions

### Solution 1: Difference Array

The problem is actually about performing addition and subtraction operations on a continuous interval, and then finding the maximum value. This can be solved using a difference array.

Since the year range in the problem is fixed, we can use an array of length $102$ to represent the population changes from 1900 to 2000. Each element in the array represents the population change in that year, with positive numbers indicating the number of births and negative numbers indicating the number of deaths.

We traverse the birth and death years of each person, and add one and subtract one from the corresponding year's population change, respectively. Then we traverse the difference array, and find the maximum value of the prefix sum of the difference array. The year corresponding to the maximum value is the answer.

The time complexity is $O(n)$, and the space complexity is $O(C)$. Here, $n$ is the length of the birth and death years, and $C$ is the range of years.

<!-- tabs:start -->

```python
class Solution:
    def maxAliveYear(self, birth: List[int], death: List[int]) -> int:
        base = 1900
        d = [0] * 102
        for a, b in zip(birth, death):
            d[a - base] += 1
            d[b + 1 - base] -= 1
        s = mx = 0
        ans = 0
        for i, x in enumerate(d):
            s += x
            if mx < s:
                mx = s
                ans = base + i
        return ans
```

```java
class Solution {
    public int maxAliveYear(int[] birth, int[] death) {
        int base = 1900;
        int[] d = new int[102];
        for (int i = 0; i < birth.length; ++i) {
            int a = birth[i] - base;
            int b = death[i] - base;
            ++d[a];
            --d[b + 1];
        }
        int s = 0, mx = 0;
        int ans = 0;
        for (int i = 0; i < d.length; ++i) {
            s += d[i];
            if (mx < s) {
                mx = s;
                ans = base + i;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maxAliveYear(vector<int>& birth, vector<int>& death) {
        int base = 1900;
        int d[102]{};
        for (int i = 0; i < birth.size(); ++i) {
            int a = birth[i] - base;
            int b = death[i] - base;
            ++d[a];
            --d[b + 1];
        }
        int s = 0, mx = 0;
        int ans = 0;
        for (int i = 0; i < 102; ++i) {
            s += d[i];
            if (mx < s) {
                mx = s;
                ans = base + i;
            }
        }
        return ans;
    }
};
```

```go
func maxAliveYear(birth []int, death []int) (ans int) {
	base := 1900
	d := [102]int{}
	for i, a := range birth {
		a -= base
		b := death[i] - base
		d[a]++
		d[b+1]--
	}
	mx, s := 0, 0
	for i, x := range d {
		s += x
		if mx < s {
			mx = s
			ans = base + i
		}
	}
	return
}
```

```ts
function maxAliveYear(birth: number[], death: number[]): number {
    const base = 1900;
    const d: number[] = Array(102).fill(0);
    for (let i = 0; i < birth.length; ++i) {
        const [a, b] = [birth[i] - base, death[i] - base];
        ++d[a];
        --d[b + 1];
    }
    let [s, mx] = [0, 0];
    let ans = 0;
    for (let i = 0; i < d.length; ++i) {
        s += d[i];
        if (mx < s) {
            mx = s;
            ans = base + i;
        }
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn max_alive_year(birth: Vec<i32>, death: Vec<i32>) -> i32 {
        let n = birth.len();
        let mut d = vec![0; 102];
        let base = 1900;
        for i in 0..n {
            d[(birth[i] - base) as usize] += 1;
            d[(death[i] - base + 1) as usize] -= 1;
        }
        let mut ans = 0;
        let mut mx = 0;
        let mut s = 0;
        for i in 0..102 {
            s += d[i];
            if mx < s {
                mx = s;
                ans = base + (i as i32);
            }
        }
        ans
    }
}
```

```swift
class Solution {
    func maxAliveYear(_ birth: [Int], _ death: [Int]) -> Int {
        let base = 1900
        var delta = Array(repeating: 0, count: 102) // Array to hold the changes

        for i in 0..<birth.count {
            let start = birth[i] - base
            let end = death[i] - base
            delta[start] += 1
            if end + 1 < delta.count {
                delta[end + 1] -= 1
            }
        }

        var maxAlive = 0, currentAlive = 0, maxYear = 0
        for year in 0..<delta.count {
            currentAlive += delta[year]
            if currentAlive > maxAlive {
                maxAlive = currentAlive
                maxYear = year + base
            }
        }

        return maxYear
    }
}
```

<!-- tabs:end -->

<!-- end -->
