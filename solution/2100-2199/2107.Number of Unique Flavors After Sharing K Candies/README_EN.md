# [2107. Number of Unique Flavors After Sharing K Candies](https://leetcode.com/problems/number-of-unique-flavors-after-sharing-k-candies)

[中文文档](/solution/2100-2199/2107.Number%20of%20Unique%20Flavors%20After%20Sharing%20K%20Candies/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>candies</code>, where <code>candies[i]</code> represents the flavor of the <code>i<sup>th</sup></code> candy. Your mom wants you to share these candies with your little sister by giving her <code>k</code> <strong>consecutive</strong> candies, but you want to keep as many flavors of candies as possible.</p>

<p>Return <em>the <strong>maximum</strong> number of <strong>unique</strong> flavors of candy you can keep after sharing </em><em> with your sister.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> candies = [1,<u>2,2,3</u>,4,3], k = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong> 
Give the candies in the range [1, 3] (inclusive) with flavors [2,2,3].
You can eat candies with flavors [1,4,3].
There are 3 unique flavors, so return 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> candies = [2,2,2,<u>2,3</u>,3], k = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> 
Give the candies in the range [3, 4] (inclusive) with flavors [2,3].
You can eat candies with flavors [2,2,2,3].
There are 2 unique flavors, so return 2.
Note that you can also share the candies with flavors [2,2] and eat the candies with flavors [2,2,3,3].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> candies = [2,4,5], k = 0
<strong>Output:</strong> 3
<strong>Explanation:</strong> 
You do not have to give any candies.
You can eat the candies with flavors [2,4,5].
There are 3 unique flavors, so return 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= candies.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= candies[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= k &lt;= candies.length</code></li>
</ul>

## Solutions

**Solution 1: Sliding Window + Hash Table**

We can maintain a sliding window of size $k$, where the candies outside the window are for ourselves, and the $k$ candies inside the window are shared with our sister and mother. We can use a hash table $cnt$ to record the flavors of the candies outside the window and their corresponding quantities.

Initially, the hash table $cnt$ stores the flavors of the candies from $candies[k]$ to $candies[n-1]$ and their corresponding quantities. At this time, the number of candy flavors is the size of the hash table $cnt$, that is, $ans = cnt.size()$.

Next, we traverse the candies in the range $[k,..n-1]$, add the current candy $candies[i]$ to the window, and move the candy $candies[i-k]$ on the left side of the window out of the window. Then we update the hash table $cnt$, and update the number of candy flavors $ans$ to $max(ans, cnt.size())$.

After traversing all the candies, we can get the maximum number of unique flavors of candies that can be retained.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the number of candies.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def shareCandies(self, candies: List[int], k: int) -> int:
        cnt = Counter(candies[k:])
        ans = len(cnt)
        for i in range(k, len(candies)):
            cnt[candies[i - k]] += 1
            cnt[candies[i]] -= 1
            if cnt[candies[i]] == 0:
                cnt.pop(candies[i])
            ans = max(ans, len(cnt))
        return ans
```

### **Java**

```java
class Solution {
    public int shareCandies(int[] candies, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int n = candies.length;
        for (int i = k; i < n; ++i) {
            cnt.merge(candies[i], 1, Integer::sum);
        }
        int ans = cnt.size();
        for (int i = k; i < n; ++i) {
            cnt.merge(candies[i - k], 1, Integer::sum);
            if (cnt.merge(candies[i], -1, Integer::sum) == 0) {
                cnt.remove(candies[i]);
            }
            ans = Math.max(ans, cnt.size());
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int shareCandies(vector<int>& candies, int k) {
        unordered_map<int, int> cnt;
        int n = candies.size();
        for (int i = k; i < n; ++i) {
            ++cnt[candies[i]];
        }
        int ans = cnt.size();
        for (int i = k; i < n; ++i) {
            ++cnt[candies[i - k]];
            if (--cnt[candies[i]] == 0) {
                cnt.erase(candies[i]);
            }
            ans = max(ans, (int) cnt.size());
        }
        return ans;
    }
};
```

### **Go**

```go
func shareCandies(candies []int, k int) (ans int) {
	cnt := map[int]int{}
	for _, c := range candies[k:] {
		cnt[c]++
	}
	ans = len(cnt)
	for i := k; i < len(candies); i++ {
		cnt[candies[i-k]]++
		cnt[candies[i]]--
		if cnt[candies[i]] == 0 {
			delete(cnt, candies[i])
		}
		ans = max(ans, len(cnt))
	}
	return
}
```

### **Rust**

```rust
use std::collections::HashMap;

impl Solution {
    pub fn share_candies(candies: Vec<i32>, k: i32) -> i32 {
        let mut cnt = HashMap::new();
        let n = candies.len();

        for i in k as usize..n {
            *cnt.entry(candies[i]).or_insert(0) += 1;
        }

        let mut ans = cnt.len() as i32;

        for i in k as usize..n {
            *cnt.entry(candies[i - k as usize]).or_insert(0) += 1;
            if let Some(x) = cnt.get_mut(&candies[i]) {
                *x -= 1;
                if *x == 0 {
                    cnt.remove(&candies[i]);
                }
            }

            ans = ans.max(cnt.len() as i32);
        }

        ans
    }
}
```

### **TypeScript**

```ts
function shareCandies(candies: number[], k: number): number {
    const cnt: Map<number, number> = new Map();
    for (const x of candies.slice(k)) {
        cnt.set(x, (cnt.get(x) || 0) + 1);
    }
    let ans = cnt.size;
    for (let i = k; i < candies.length; ++i) {
        cnt.set(candies[i - k], (cnt.get(candies[i - k]) || 0) + 1);
        cnt.set(candies[i], (cnt.get(candies[i]) || 0) - 1);
        if (cnt.get(candies[i]) === 0) {
            cnt.delete(candies[i]);
        }
        ans = Math.max(ans, cnt.size);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
