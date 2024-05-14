# [1604. Alert Using Same Key-Card Three or More Times in a One Hour Period](https://leetcode.com/problems/alert-using-same-key-card-three-or-more-times-in-a-one-hour-period)

[中文文档](/solution/1600-1699/1604.Alert%20Using%20Same%20Key-Card%20Three%20or%20More%20Times%20in%20a%20One%20Hour%20Period/README.md)

<!-- tags:Array,Hash Table,String,Sorting -->

<!-- difficulty:Medium -->

## Description

<p>LeetCode company workers use key-cards to unlock office doors. Each time a worker uses their key-card, the security system saves the worker&#39;s name and the time when it was used. The system emits an <strong>alert</strong> if any worker uses the key-card <strong>three or more times</strong> in a one-hour period.</p>

<p>You are given a list of strings <code>keyName</code> and <code>keyTime</code> where <code>[keyName[i], keyTime[i]]</code> corresponds to a person&#39;s name and the time when their key-card was used <strong>in a</strong> <strong>single day</strong>.</p>

<p>Access times are given in the <strong>24-hour time format &quot;HH:MM&quot;</strong>, such as <code>&quot;23:51&quot;</code> and <code>&quot;09:49&quot;</code>.</p>

<p>Return a <em>list of unique worker names who received an alert for frequent keycard use</em>. Sort the names in <strong>ascending order alphabetically</strong>.</p>

<p>Notice that <code>&quot;10:00&quot;</code> - <code>&quot;11:00&quot;</code> is considered to be within a one-hour period, while <code>&quot;22:51&quot;</code> - <code>&quot;23:52&quot;</code> is not considered to be within a one-hour period.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> keyName = [&quot;daniel&quot;,&quot;daniel&quot;,&quot;daniel&quot;,&quot;luis&quot;,&quot;luis&quot;,&quot;luis&quot;,&quot;luis&quot;], keyTime = [&quot;10:00&quot;,&quot;10:40&quot;,&quot;11:00&quot;,&quot;09:00&quot;,&quot;11:00&quot;,&quot;13:00&quot;,&quot;15:00&quot;]
<strong>Output:</strong> [&quot;daniel&quot;]
<strong>Explanation:</strong> &quot;daniel&quot; used the keycard 3 times in a one-hour period (&quot;10:00&quot;,&quot;10:40&quot;, &quot;11:00&quot;).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> keyName = [&quot;alice&quot;,&quot;alice&quot;,&quot;alice&quot;,&quot;bob&quot;,&quot;bob&quot;,&quot;bob&quot;,&quot;bob&quot;], keyTime = [&quot;12:01&quot;,&quot;12:00&quot;,&quot;18:00&quot;,&quot;21:00&quot;,&quot;21:20&quot;,&quot;21:30&quot;,&quot;23:00&quot;]
<strong>Output:</strong> [&quot;bob&quot;]
<strong>Explanation:</strong> &quot;bob&quot; used the keycard 3 times in a one-hour period (&quot;21:00&quot;,&quot;21:20&quot;, &quot;21:30&quot;).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= keyName.length, keyTime.length &lt;= 10<sup>5</sup></code></li>
	<li><code>keyName.length == keyTime.length</code></li>
	<li><code>keyTime[i]</code> is in the format <strong>&quot;HH:MM&quot;</strong>.</li>
	<li><code>[keyName[i], keyTime[i]]</code> is <strong>unique</strong>.</li>
	<li><code>1 &lt;= keyName[i].length &lt;= 10</code></li>
	<li><code>keyName[i] contains only lowercase English letters.</code></li>
</ul>

## Solutions

### Solution 1: Hash Table + Sorting

First, we use a hash table $d$ to record all the clock-in times of each employee.

Then we traverse the hash table. For each employee, we first check whether the number of clock-in times is greater than or equal to 3. If not, we skip this employee. Otherwise, we sort all the clock-in times of this employee in chronological order, and then traverse the sorted clock-in times to check whether the two times at a distance of 2 indices are within the same hour. If so, we add this employee to the answer array.

Finally, we sort the answer array in lexicographical order to get the answer.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Where $n$ is the number of clock-in records.

<!-- tabs:start -->

```python
class Solution:
    def alertNames(self, keyName: List[str], keyTime: List[str]) -> List[str]:
        d = defaultdict(list)
        for name, t in zip(keyName, keyTime):
            t = int(t[:2]) * 60 + int(t[3:])
            d[name].append(t)
        ans = []
        for name, ts in d.items():
            if (n := len(ts)) > 2:
                ts.sort()
                for i in range(n - 2):
                    if ts[i + 2] - ts[i] <= 60:
                        ans.append(name)
                        break
        ans.sort()
        return ans
```

```java
class Solution {
    public List<String> alertNames(String[] keyName, String[] keyTime) {
        Map<String, List<Integer>> d = new HashMap<>();
        for (int i = 0; i < keyName.length; ++i) {
            String name = keyName[i];
            String time = keyTime[i];
            int t
                = Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3));
            d.computeIfAbsent(name, k -> new ArrayList<>()).add(t);
        }
        List<String> ans = new ArrayList<>();
        for (var e : d.entrySet()) {
            var ts = e.getValue();
            int n = ts.size();
            if (n > 2) {
                Collections.sort(ts);
                for (int i = 0; i < n - 2; ++i) {
                    if (ts.get(i + 2) - ts.get(i) <= 60) {
                        ans.add(e.getKey());
                        break;
                    }
                }
            }
        }
        Collections.sort(ans);
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<string> alertNames(vector<string>& keyName, vector<string>& keyTime) {
        unordered_map<string, vector<int>> d;
        for (int i = 0; i < keyName.size(); ++i) {
            auto name = keyName[i];
            auto time = keyTime[i];
            int a, b;
            sscanf(time.c_str(), "%d:%d", &a, &b);
            int t = a * 60 + b;
            d[name].emplace_back(t);
        }
        vector<string> ans;
        for (auto& [name, ts] : d) {
            int n = ts.size();
            if (n > 2) {
                sort(ts.begin(), ts.end());
                for (int i = 0; i < n - 2; ++i) {
                    if (ts[i + 2] - ts[i] <= 60) {
                        ans.emplace_back(name);
                        break;
                    }
                }
            }
        }
        sort(ans.begin(), ans.end());
        return ans;
    }
};
```

```go
func alertNames(keyName []string, keyTime []string) (ans []string) {
	d := map[string][]int{}
	for i, name := range keyName {
		var a, b int
		fmt.Sscanf(keyTime[i], "%d:%d", &a, &b)
		t := a*60 + b
		d[name] = append(d[name], t)
	}
	for name, ts := range d {
		n := len(ts)
		if n > 2 {
			sort.Ints(ts)
			for i := 0; i < n-2; i++ {
				if ts[i+2]-ts[i] <= 60 {
					ans = append(ans, name)
					break
				}
			}
		}
	}
	sort.Strings(ans)
	return
}
```

```ts
function alertNames(keyName: string[], keyTime: string[]): string[] {
    const d: { [name: string]: number[] } = {};
    for (let i = 0; i < keyName.length; ++i) {
        const name = keyName[i];
        const t = keyTime[i];
        const minutes = +t.slice(0, 2) * 60 + +t.slice(3);
        if (d[name] === undefined) {
            d[name] = [];
        }
        d[name].push(minutes);
    }
    const ans: string[] = [];
    for (const name in d) {
        if (d.hasOwnProperty(name)) {
            const ts = d[name];
            if (ts.length > 2) {
                ts.sort((a, b) => a - b);
                for (let i = 0; i < ts.length - 2; ++i) {
                    if (ts[i + 2] - ts[i] <= 60) {
                        ans.push(name);
                        break;
                    }
                }
            }
        }
    }
    ans.sort();
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
