# [2391. Minimum Amount of Time to Collect Garbage](https://leetcode.com/problems/minimum-amount-of-time-to-collect-garbage)

[中文文档](/solution/2300-2399/2391.Minimum%20Amount%20of%20Time%20to%20Collect%20Garbage/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> array of strings <code>garbage</code> where <code>garbage[i]</code> represents the assortment of garbage at the <code>i<sup>th</sup></code> house. <code>garbage[i]</code> consists only of the characters <code>&#39;M&#39;</code>, <code>&#39;P&#39;</code> and <code>&#39;G&#39;</code> representing one unit of metal, paper and glass garbage respectively. Picking up <strong>one</strong> unit of any type of garbage takes <code>1</code> minute.</p>

<p>You are also given a <strong>0-indexed</strong> integer array <code>travel</code> where <code>travel[i]</code> is the number of minutes needed to go from house <code>i</code> to house <code>i + 1</code>.</p>

<p>There are three garbage trucks in the city, each responsible for picking up one type of garbage. Each garbage truck starts at house <code>0</code> and must visit each house <strong>in order</strong>; however, they do <strong>not</strong> need to visit every house.</p>

<p>Only <strong>one</strong> garbage truck may be used at any given moment. While one truck is driving or picking up garbage, the other two trucks <strong>cannot</strong> do anything.</p>

<p>Return<em> the <strong>minimum</strong> number of minutes needed to pick up all the garbage.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

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

<p><strong>Example 2:</strong></p>

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

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def garbageCollection(self, garbage: List[str], travel: List[int]) -> int:
        def f(c):
            tot = sum(v.count(c) for v in garbage)
            res = 0
            for i, v in enumerate(garbage):
                t = v.count(c)
                res += t
                tot -= t
                if tot == 0:
                    break
                if i < n - 1:
                    res += travel[i]
            return res

        n = len(garbage)
        return f('M') + f('P') + f('G')
```

```python
class Solution:
    def garbageCollection(self, garbage: List[str], travel: List[int]) -> int:
        ans = 0
        pos = {}
        for i, v in enumerate(garbage):
            ans += len(v)
            for c in v:
                pos[c] = i
        s = list(accumulate(travel, initial=0))
        ans += sum(s[i] for i in pos.values())
        return ans
```

### **Java**

```java
class Solution {
    private String[] g;
    private int[] travel;

    public int garbageCollection(String[] garbage, int[] travel) {
        g = garbage;
        this.travel = travel;
        return f('M') + f('P') + f('G');
    }

    private int f(char c) {
        int tot = 0;
        for (var v : g) {
            for (char ch : v.toCharArray()) {
                if (ch == c) {
                    ++tot;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < g.length; ++i) {
            int t = 0;
            for (char ch : g[i].toCharArray()) {
                if (ch == c) {
                    ++t;
                }
            }
            res += t;
            tot -= t;
            if (tot == 0) {
                break;
            }
            if (i < g.length - 1) {
                res += travel[i];
            }
        }
        return res;
    }
}
```

```java
class Solution {
    public int garbageCollection(String[] garbage, int[] travel) {
        int ans = 0;
        int[] pos = new int[26];
        for (int i = 0; i < garbage.length; ++i) {
            ans += garbage[i].length();
            for (char c : garbage[i].toCharArray()) {
                pos[c - 'A'] = i;
            }
        }
        int[] s = new int[travel.length + 1];
        for (int i = 0; i < travel.length; ++i) {
            s[i + 1] = s[i] + travel[i];
        }
        for (int i : pos) {
            ans += s[i];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> g;
    vector<int> travel;

    int garbageCollection(vector<string>& garbage, vector<int>& travel) {
        g = garbage;
        this->travel = travel;
        return f('M') + f('P') + f('G');
    }

    int f(char c) {
        int tot = 0;
        for (string& v : g) {
            for (char ch : v) {
                tot += ch == c;
            }
        }
        int res = 0;
        for (int i = 0; i < g.size(); ++i) {
            int t = 0;
            for (char ch : g[i]) {
                t += ch == c;
            }
            res += t;
            tot -= t;
            if (tot == 0) break;
            if (i < g.size() - 1) res += travel[i];
        }
        return res;
    }
};
```

```cpp
class Solution {
public:
    int garbageCollection(vector<string>& garbage, vector<int>& travel) {
        int ans = 0;
        vector<int> pos(26);
        for (int i = 0; i < garbage.size(); ++i) {
            ans += garbage[i].size();
            for (char c : garbage[i]) {
                pos[c - 'A'] = i;
            }
        }
        vector<int> s(travel.size() + 1);
        for (int i = 0; i < travel.size(); ++i) {
            s[i + 1] = s[i] + travel[i];
        }
        for (int i : pos) {
            ans += s[i];
        }
        return ans;
    }
};
```

### **Go**

```go
func garbageCollection(garbage []string, travel []int) int {
	n := len(garbage)
	f := func(c rune) int {
		tot := 0
		for _, v := range garbage {
			for _, ch := range v {
				if ch == c {
					tot++
				}
			}
		}
		res := 0
		for i, v := range garbage {
			t := 0
			for _, ch := range v {
				if ch == c {
					t++
				}
			}
			res += t
			tot -= t
			if tot == 0 {
				break
			}
			if i < n-1 {
				res += travel[i]
			}
		}
		return res
	}
	return f('M') + f('P') + f('G')
}
```

```go
func garbageCollection(garbage []string, travel []int) int {
	ans := 0
	pos := map[rune]int{}
	for i, v := range garbage {
		ans += len(v)
		for _, c := range v {
			pos[c] = i
		}
	}
	s := make([]int, len(travel)+1)
	for i, v := range travel {
		s[i+1] = s[i] + v
	}
	for _, i := range pos {
		ans += s[i]
	}
	return ans
}
```

### **TypeScript**

```ts
function garbageCollection(garbage: string[], travel: number[]): number {
    const n = garbage.length;
    const count = [0, 0, 0];
    const cs = ['G', 'P', 'M'];
    for (const s of garbage) {
        for (const c of s) {
            if (c === 'G') {
                count[0]++;
            } else if (c === 'P') {
                count[1]++;
            } else if (c === 'M') {
                count[2]++;
            }
        }
    }

    let res = 0;
    for (let i = 0; i < 3; i++) {
        for (let j = 0; j < n; j++) {
            const s = garbage[j];
            for (const c of s) {
                if (c === cs[i]) {
                    res++;
                    count[i]--;
                }
            }
            if (count[i] === 0) {
                break;
            }
            res += travel[j];
        }
    }
    return res;
}
```

```ts
function garbageCollection(garbage: string[], travel: number[]): number {
    let ans = 0;
    let pos = new Map();
    for (let i = 0; i < garbage.length; ++i) {
        ans += garbage[i].length;
        for (const c of garbage[i]) {
            pos.set(c, i);
        }
    }
    let s = new Array(travel.length + 1).fill(0);
    for (let i = 0; i < travel.length; ++i) {
        s[i + 1] = s[i] + travel[i];
    }
    for (const [_, i] of pos) {
        ans += s[i];
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn garbage_collection(garbage: Vec<String>, travel: Vec<i32>) -> i32 {
        let n = garbage.len();
        let cs = [b'M', b'P', b'G'];
        let mut count = [0, 0, 0];
        for s in garbage.iter() {
            for c in s.as_bytes().iter() {
                count[if c == &b'M' {
                    0
                } else if c == &b'P' {
                    1
                } else {
                    2
                }] += 1;
            }
        }

        let mut res = 0;
        for i in 0..3 {
            for j in 0..n {
                let s = &garbage[j];
                for c in s.as_bytes().iter() {
                    if c == &cs[i] {
                        res += 1;
                        count[i] -= 1;
                    }
                }
                if count[i] == 0 {
                    break;
                }

                res += travel[j];
            }
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
