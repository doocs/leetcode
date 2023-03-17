# [2391. Minimum Amount of Time to Collect Garbage](https://leetcode.com/problems/minimum-amount-of-time-to-collect-garbage)

[中文文档](/solution/2300-2399/2391.Minimum%20Amount%20of%20Time%20to%20Collect%20Garbage/README.md)

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

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def garbageCollection(self, garbage: List[str], travel: List[int]) -> int:
        ans = 0
        last = {}
        for i, s in enumerate(garbage):
            ans += len(s)
            for c in s:
                last[c] = i
        s = list(accumulate(travel, initial=0))
        ans += sum(s[i] for i in last.values())
        return ans
```

```python
class Solution:
    def garbageCollection(self, garbage: List[str], travel: List[int]) -> int:
        def f(x: str) -> int:
            ans = 0
            st = 0
            for i, s in enumerate(garbage):
                if t := s.count(x):
                    ans += t + st
                    st = 0
                if i < len(travel):
                    st += travel[i]
            return ans

        return f('M') + f('P') + f('G')
```

### **Java**

```java
class Solution {
    public int garbageCollection(String[] garbage, int[] travel) {
        int[] last = new int[26];
        int n = garbage.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int k = garbage[i].length();
            ans += k;
            for (int j = 0; j < k; ++j) {
                last[garbage[i].charAt(j) - 'A'] = i;
            }
        }
        int m = travel.length;
        int[] s = new int[m + 1];
        for (int i = 0; i < m; ++i) {
            s[i + 1] = s[i] + travel[i];
        }
        for (int i : last) {
            ans += s[i];
        }
        return ans;
    }
}
```

```java
class Solution {
    private String[] garbage;
    private int[] travel;

    public int garbageCollection(String[] garbage, int[] travel) {
        this.garbage = garbage;
        this.travel = travel;
        return f('M') + f('P') + f('G');
    }

    private int f(char c) {
        int ans = 0;
        int st = 0;
        for (int i = 0; i < garbage.length; ++i) {
            int cnt = 0;
            for (int j = 0; j < garbage[i].length(); ++j) {
                if (garbage[i].charAt(j) == c) {
                    ++cnt;
                }
            }
            if (cnt > 0) {
                ans += cnt + st;
                st = 0;
            }
            if (i < travel.length) {
                st += travel[i];
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int garbageCollection(vector<string>& garbage, vector<int>& travel) {
        int n = garbage.size(), m = travel.size();
        int last[26]{};
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += garbage[i].size();
            for (char& c : garbage[i]) {
                last[c - 'A'] = i;
            }
        }
        int s[m + 1];
        s[0] = 0;
        for (int i = 1; i <= m; ++i) {
            s[i] = s[i - 1] + travel[i - 1];
        }
        for (int i : last) {
            ans += s[i];
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int garbageCollection(vector<string>& garbage, vector<int>& travel) {
        auto f = [&](char x) {
            int ans = 0, st = 0;
            for (int i = 0; i < garbage.size(); ++i) {
                int cnt = 0;
                for (char& c : garbage[i]) {
                    if (c == x) {
                        ++cnt;
                    }
                }
                if (cnt > 0) {
                    ans += cnt + st;
                    st = 0;
                }
                if (i < travel.size()) {
                    st += travel[i];
                }
            }
            return ans;
        };
        return f('M') + f('P') + f('G');
    }
};
```

### **Go**

```go
func garbageCollection(garbage []string, travel []int) (ans int) {
	last := [26]int{}
	for i, s := range garbage {
		ans += len(s)
		for _, c := range s {
			last[c-'A'] = i
		}
	}
	s := make([]int, len(travel)+1)
	for i, x := range travel {
		s[i+1] = s[i] + x
	}
	for _, i := range last {
		ans += s[i]
	}
	return
}
```

```go
func garbageCollection(garbage []string, travel []int) (ans int) {
	f := func(x rune) int {
		ans, st := 0, 0
		for i, s := range garbage {
			cnt := strings.Count(s, string(x))
			if cnt > 0 {
				ans += cnt + st
				st = 0
			}
			if i < len(travel) {
				st += travel[i]
			}
		}
		return ans
	}
	return f('M') + f('P') + f('G')
}
```

### **TypeScript**

```ts
function garbageCollection(garbage: string[], travel: number[]): number {
    const n = garbage.length;
    const m = travel.length;
    let ans = 0;
    const last = new Array(26).fill(0);
    for (let i = 0; i < n; ++i) {
        ans += garbage[i].length;
        for (const c of garbage[i]) {
            last[c.charCodeAt(0) - 'A'.charCodeAt(0)] = i;
        }
    }
    const s = new Array(m + 1).fill(0);
    for (let i = 1; i <= m; ++i) {
        s[i] = s[i - 1] + travel[i - 1];
    }
    for (const i of last) {
        ans += s[i];
    }
    return ans;
}
```

```ts
function garbageCollection(garbage: string[], travel: number[]): number {
    const f = (x: string) => {
        let ans = 0;
        let st = 0;
        for (let i = 0; i < garbage.length; ++i) {
            let cnt = 0;
            for (const c of garbage[i]) {
                if (c === x) {
                    ++cnt;
                }
            }
            if (cnt > 0) {
                ans += cnt + st;
                st = 0;
            }
            if (i < travel.length) {
                st += travel[i];
            }
        }
        return ans;
    };
    return f('M') + f('P') + f('G');
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
