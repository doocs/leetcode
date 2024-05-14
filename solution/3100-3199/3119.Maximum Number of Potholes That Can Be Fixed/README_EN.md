# [3119. Maximum Number of Potholes That Can Be Fixed 🔒](https://leetcode.com/problems/maximum-number-of-potholes-that-can-be-fixed)

[中文文档](/solution/3100-3199/3119.Maximum%20Number%20of%20Potholes%20That%20Can%20Be%20Fixed/README.md)

<!-- tags:Greedy,String,Sorting -->

<!-- difficulty:Medium -->

## Description

<p>You are given a string <code>road</code>, consisting only of characters <code>&quot;x&quot;</code> and <code>&quot;.&quot;</code>, where each <code>&quot;x&quot;</code> denotes a <em>pothole</em> and each <code>&quot;.&quot;</code> denotes a smooth road, and an integer <code>budget</code>.</p>

<p>In one repair operation, you can repair <code>n</code> <strong>consecutive</strong> potholes for a price of <code>n + 1</code>.</p>

<p>Return the <strong>maximum</strong> number of potholes that can be fixed such that the sum of the prices of all of the fixes <strong>doesn&#39;t go over</strong> the given budget.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">road = &quot;..&quot;, budget = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>There are no potholes to be fixed.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">road = &quot;..xxxxx&quot;, budget = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>We fix the first three potholes (they are consecutive). The budget needed for this task is <code>3 + 1 = 4</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">road = &quot;x.x.xxx...x&quot;, budget = 14</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>We can fix all the potholes. The total cost would be <code>(1 + 1) + (1 + 1) + (3 + 1) + (1 + 1) = 10</code> which is within our budget of 14.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= road.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= budget &lt;= 10<sup>5</sup> + 1</code></li>
	<li><code>road</code> consists only of characters <code>&#39;.&#39;</code> and <code>&#39;x&#39;</code>.</li>
</ul>

## Solutions

### Solution 1: Counting + Greedy

First, we count the number of each continuous pothole, recorded in the array $cnt$, i.e., $cnt[k]$ represents there are $cnt[k]$ continuous potholes of length $k$.

Since we want to repair as many potholes as possible, and for a continuous pothole of length $k$, we need to spend a cost of $k + 1$, we should prioritize repairing longer potholes to minimize the cost.

Therefore, we start repairing from the longest pothole. For a pothole of length $k$, the maximum number we can repair is $t = \min(\text{budget} / (k + 1), \text{cnt}[k])$. We add the number of repairs multiplied by the length $k$ to the answer, then update the remaining budget. For the remaining $cnt[k] - t$ potholes of length $k$, we merge them into the potholes of length $k - 1$. Continue this process until all potholes are traversed.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the string $road$.

<!-- tabs:start -->

```python
class Solution:
    def maxPotholes(self, road: str, budget: int) -> int:
        road += "."
        n = len(road)
        cnt = [0] * n
        k = 0
        for c in road:
            if c == "x":
                k += 1
            elif k:
                cnt[k] += 1
                k = 0
        ans = 0
        for k in range(n - 1, 0, -1):
            if cnt[k] == 0:
                continue
            t = min(budget // (k + 1), cnt[k])
            ans += t * k
            budget -= t * (k + 1)
            if budget == 0:
                break
            cnt[k - 1] += cnt[k] - t
        return ans
```

```java
class Solution {
    public int maxPotholes(String road, int budget) {
        road += ".";
        int n = road.length();
        int[] cnt = new int[n];
        int k = 0;
        for (char c : road.toCharArray()) {
            if (c == 'x') {
                ++k;
            } else if (k > 0) {
                ++cnt[k];
                k = 0;
            }
        }
        int ans = 0;
        for (k = n - 1; k > 0 && budget > 0; --k) {
            int t = Math.min(budget / (k + 1), cnt[k]);
            ans += t * k;
            budget -= t * (k + 1);
            cnt[k - 1] += cnt[k] - t;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maxPotholes(string road, int budget) {
        road.push_back('.');
        int n = road.size();
        vector<int> cnt(n);
        int k = 0;
        for (char& c : road) {
            if (c == 'x') {
                ++k;
            } else if (k) {
                ++cnt[k];
                k = 0;
            }
        }
        int ans = 0;
        for (k = n - 1; k && budget; --k) {
            int t = min(budget / (k + 1), cnt[k]);
            ans += t * k;
            budget -= t * (k + 1);
            cnt[k - 1] += cnt[k] - t;
        }
        return ans;
    }
};
```

```go
func maxPotholes(road string, budget int) (ans int) {
	road += "."
	n := len(road)
	cnt := make([]int, n)
	k := 0
	for _, c := range road {
		if c == 'x' {
			k++
		} else if k > 0 {
			cnt[k]++
			k = 0
		}
	}
	for k = n - 1; k > 0 && budget > 0; k-- {
		t := min(budget/(k+1), cnt[k])
		ans += t * k
		budget -= t * (k + 1)
		cnt[k-1] += cnt[k] - t
	}
	return
}
```

```ts
function maxPotholes(road: string, budget: number): number {
    road += '.';
    const n = road.length;
    const cnt: number[] = Array(n).fill(0);
    let k = 0;
    for (const c of road) {
        if (c === 'x') {
            ++k;
        } else if (k) {
            ++cnt[k];
            k = 0;
        }
    }
    let ans = 0;
    for (k = n - 1; k && budget; --k) {
        const t = Math.min(Math.floor(budget / (k + 1)), cnt[k]);
        ans += t * k;
        budget -= t * (k + 1);
        cnt[k - 1] += cnt[k] - t;
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn max_potholes(road: String, budget: i32) -> i32 {
        let mut cs: Vec<char> = road.chars().collect();
        cs.push('.');
        let n = cs.len();
        let mut cnt: Vec<i32> = vec![0; n];
        let mut k = 0;

        for c in cs.iter() {
            if *c == 'x' {
                k += 1;
            } else if k > 0 {
                cnt[k] += 1;
                k = 0;
            }
        }

        let mut ans = 0;
        let mut budget = budget;

        for k in (1..n).rev() {
            if budget == 0 {
                break;
            }
            let t = std::cmp::min(budget / ((k as i32) + 1), cnt[k]);
            ans += t * (k as i32);
            budget -= t * ((k as i32) + 1);
            cnt[k - 1] += cnt[k] - t;
        }

        ans
    }
}
```

```cs
public class Solution {
    public int MaxPotholes(string road, int budget) {
        road += '.';
        int n = road.Length;
        int[] cnt = new int[n];
        int k = 0;
        foreach (char c in road) {
            if (c == 'x') {
                ++k;
            } else if (k > 0) {
                ++cnt[k];
                k = 0;
            }
        }
        int ans = 0;
        for (k = n - 1; k > 0 && budget > 0; --k) {
            int t = Math.Min(budget / (k + 1), cnt[k]);
            ans += t * k;
            budget -= t * (k + 1);
            cnt[k - 1] += cnt[k] - t;
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- end -->
