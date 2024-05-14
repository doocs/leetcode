# [2391. Minimum Amount of Time to Collect Garbage](https://leetcode.com/problems/minimum-amount-of-time-to-collect-garbage)

[中文文档](/solution/2300-2399/2391.Minimum%20Amount%20of%20Time%20to%20Collect%20Garbage/README.md)

<!-- tags:Array,String,Prefix Sum -->

<!-- difficulty:Medium -->

## Description

<p>You are given a <strong>0-indexed</strong> array of strings <code>garbage</code> where <code>garbage[i]</code> represents the assortment of garbage at the <code>i<sup>th</sup></code> house. <code>garbage[i]</code> consists only of the characters <code>&#39;M&#39;</code>, <code>&#39;P&#39;</code> and <code>&#39;G&#39;</code> representing one unit of metal, paper and glass garbage respectively. Picking up <strong>one</strong> unit of any type of garbage takes <code>1</code> minute.</p>

<p>You are also given a <strong>0-indexed</strong> integer array <code>travel</code> where <code>travel[i]</code> is the number of minutes needed to go from house <code>i</code> to house <code>i + 1</code>.</p>

<p>There are three garbage trucks in the city, each responsible for picking up one type of garbage. Each garbage truck starts at house <code>0</code> and must visit each house <strong>in order</strong>; however, they do <strong>not</strong> need to visit every house.</p>

<p>Only <strong>one</strong> garbage truck may be used at any given moment. While one truck is driving or picking up garbage, the other two trucks <strong>cannot</strong> do anything.</p>

<p>Return<em> the <strong>minimum</strong> number of minutes needed to pick up all the garbage.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> garbage = [&quot;G&quot;,&quot;P&quot;,&quot;GP&quot;,&quot;GG&quot;], travel = [2,4,3]
<strong>Output:</strong> 21
<strong>Explanation:</strong>
The paper garbage truck:
1. Travels from house 0 to house 1
2. Collects the paper garbage at house 1
3. Travels from house 1 to house 2
4. Collects the paper garbage at house 2
Altogether, it takes 8 minutes to pick up all the paper garbage.
The glass garbage truck:
1. Collects the glass garbage at house 0
2. Travels from house 0 to house 1
3. Travels from house 1 to house 2
4. Collects the glass garbage at house 2
5. Travels from house 2 to house 3
6. Collects the glass garbage at house 3
Altogether, it takes 13 minutes to pick up all the glass garbage.
Since there is no metal garbage, we do not need to consider the metal garbage truck.
Therefore, it takes a total of 8 + 13 = 21 minutes to collect all the garbage.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> garbage = [&quot;MMM&quot;,&quot;PGM&quot;,&quot;GP&quot;], travel = [3,10]
<strong>Output:</strong> 37
<strong>Explanation:</strong>
The metal garbage truck takes 7 minutes to pick up all the metal garbage.
The paper garbage truck takes 15 minutes to pick up all the paper garbage.
The glass garbage truck takes 15 minutes to pick up all the glass garbage.
It takes a total of 7 + 15 + 15 = 37 minutes to collect all the garbage.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= garbage.length &lt;= 10<sup>5</sup></code></li>
	<li><code>garbage[i]</code> consists of only the letters <code>&#39;M&#39;</code>, <code>&#39;P&#39;</code>, and <code>&#39;G&#39;</code>.</li>
	<li><code>1 &lt;= garbage[i].length &lt;= 10</code></li>
	<li><code>travel.length == garbage.length - 1</code></li>
	<li><code>1 &lt;= travel[i] &lt;= 100</code></li>
</ul>

## Solutions

### Solution 1: Hash Table + Prefix Sum

According to the problem description, each garbage truck starts from house $0$, collects one type of garbage, and moves forward in order until it reaches the house index where this type of garbage last appears.

Therefore, we can use a hash table $\text{last}$ to record the house index where each type of garbage last appears. We assume that the $i$-th type of garbage last appears in the $j$-th house, then the driving time required for the $i$-th truck is $\text{travel}[0] + \text{travel}[1] + \cdots + \text{travel}[j-1]$. Note, if $j = 0$, no driving time is needed. We accumulate the driving time of all vehicles, add the total collection time of each type of garbage, and we can get the answer.

The time complexity is $O(n)$, and the space complexity is $O(k)$, where $n$ and $k$ are the number and types of garbage, respectively. In this problem, $k = 3$.

<!-- tabs:start -->

```python
class Solution:
    def garbageCollection(self, garbage: List[str], travel: List[int]) -> int:
        last = {}
        ans = 0
        for i, s in enumerate(garbage):
            ans += len(s)
            for c in s:
                last[c] = i
        ts = 0
        for i, t in enumerate(travel, 1):
            ts += t
            ans += sum(ts for j in last.values() if i == j)
        return ans
```

```java
class Solution {
    public int garbageCollection(String[] garbage, int[] travel) {
        Map<Character, Integer> last = new HashMap<>(3);
        int ans = 0;
        for (int i = 0; i < garbage.length; ++i) {
            String s = garbage[i];
            ans += s.length();
            for (char c : s.toCharArray()) {
                last.put(c, i);
            }
        }
        int ts = 0;
        for (int i = 1; i <= travel.length; ++i) {
            ts += travel[i - 1];
            for (int j : last.values()) {
                if (i == j) {
                    ans += ts;
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int garbageCollection(vector<string>& garbage, vector<int>& travel) {
        unordered_map<char, int> last;
        int ans = 0;
        for (int i = 0; i < garbage.size(); ++i) {
            auto& s = garbage[i];
            ans += s.size();
            for (char& c : s) {
                last[c] = i;
            }
        }
        int ts = 0;
        for (int i = 1; i <= travel.size(); ++i) {
            ts += travel[i - 1];
            for (auto& [_, j] : last) {
                if (i == j) {
                    ans += ts;
                }
            }
        }
        return ans;
    }
};
```

```go
func garbageCollection(garbage []string, travel []int) (ans int) {
	last := map[byte]int{}
	for i, s := range garbage {
		ans += len(s)
		for j := range s {
			last[s[j]] = i
		}
	}
	ts := 0
	for i := 1; i <= len(travel); i++ {
		ts += travel[i-1]
		for _, j := range last {
			if i == j {
				ans += ts
			}
		}
	}
	return
}
```

```ts
function garbageCollection(garbage: string[], travel: number[]): number {
    const last: Map<string, number> = new Map();
    let ans = 0;
    for (let i = 0; i < garbage.length; ++i) {
        const s = garbage[i];
        ans += s.length;
        for (const c of s) {
            last.set(c, i);
        }
    }
    let ts = 0;
    for (let i = 1; i <= travel.length; ++i) {
        ts += travel[i - 1];
        for (const [_, j] of last) {
            if (i === j) {
                ans += ts;
            }
        }
    }
    return ans;
}
```

```rust
use std::collections::HashMap;

impl Solution {
    pub fn garbage_collection(garbage: Vec<String>, travel: Vec<i32>) -> i32 {
        let mut last: HashMap<char, usize> = HashMap::new();
        let mut ans = 0;
        for (i, s) in garbage.iter().enumerate() {
            ans += s.len() as i32;
            for c in s.chars() {
                last.insert(c, i);
            }
        }
        let mut ts = 0;
        for (i, t) in travel.iter().enumerate() {
            ts += t;
            for &j in last.values() {
                if i + 1 == j {
                    ans += ts;
                }
            }
        }
        ans
    }
}
```

```cs
public class Solution {
    public int GarbageCollection(string[] garbage, int[] travel) {
        Dictionary<char, int> last = new Dictionary<char, int>();
        int ans = 0;
        for (int i = 0; i < garbage.Length; ++i) {
            ans += garbage[i].Length;
            foreach (char c in garbage[i]) {
                last[c] = i;
            }
        }
        int ts = 0;
        for (int i = 1; i <= travel.Length; ++i) {
            ts += travel[i - 1];
            foreach (int j in last.Values) {
                if (i == j) {
                    ans += ts;
                }
            }
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- end -->
