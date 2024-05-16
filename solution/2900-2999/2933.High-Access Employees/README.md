---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2933.High-Access%20Employees/README.md
rating: 1536
source: 第 371 场周赛 Q2
tags:
    - 数组
    - 哈希表
    - 字符串
    - 排序
---

# [2933. 高访问员工](https://leetcode.cn/problems/high-access-employees)

[English Version](/solution/2900-2999/2933.High-Access%20Employees/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code> 、下标从 <strong>0</strong> 开始的二维字符串数组 <code>access_times</code> 。对于每个 <code>i</code>（<code>0 &lt;= i &lt;= n - 1</code> ），<code>access_times[i][0]</code> 表示某位员工的姓名，<code>access_times[i][1]</code> 表示该员工的访问时间。<code>access_times</code> 中的所有条目都发生在同一天内。</p>

<p>访问时间用 <strong>四位</strong> 数字表示， 符合 <strong>24 小时制</strong> ，例如 <code>"0800"</code> 或 <code>"2250"</code> 。</p>

<p>如果员工在 <strong>同一小时内</strong> 访问系统 <strong>三次或更多</strong> ，则称其为 <strong>高访问</strong> 员工。</p>

<p>时间间隔正好相差一小时的时间 <strong>不</strong> 被视为同一小时内。例如，<code>"0815"</code> 和 <code>"0915"</code> 不属于同一小时内。</p>

<p>一天开始和结束时的访问时间不被计算为同一小时内。例如，<code>"0005"</code> 和 <code>"2350"</code> 不属于同一小时内。</p>

<p>以列表形式，按任意顺序，返回所有 <strong>高访问</strong> 员工的姓名。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>access_times = [["a","0549"],["b","0457"],["a","0532"],["a","0621"],["b","0540"]]
<strong>输出：</strong>["a"]
<strong>解释：</strong>"a" 在时间段 [05:32, 06:31] 内有三条访问记录，时间分别为 05:32 、05:49 和 06:21 。
但是 "b" 的访问记录只有两条。
因此，答案是 ["a"] 。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>access_times = [["d","0002"],["c","0808"],["c","0829"],["e","0215"],["d","1508"],["d","1444"],["d","1410"],["c","0809"]]
<strong>输出：</strong>["c","d"]
<strong>解释：</strong>"c" 在时间段 [08:08, 09:07] 内有三条访问记录，时间分别为 08:08 、08:09 和 08:29 。
"d" 在时间段 [14:10, 15:09] 内有三条访问记录，时间分别为 14:10 、14:44 和 15:08 。
然而，"e" 只有一条访问记录，因此不能包含在答案中，最终答案是 ["c","d"] 。</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>access_times = [["cd","1025"],["ab","1025"],["cd","1046"],["cd","1055"],["ab","1124"],["ab","1120"]]
<strong>输出：</strong>["ab","cd"]
<strong>解释：</strong>"ab"在时间段 [10:25, 11:24] 内有三条访问记录，时间分别为 10:25 、11:20 和 11:24 。
"cd" 在时间段 [10:25, 11:24] 内有三条访问记录，时间分别为 10:25 、10:46 和 10:55 。
因此，答案是 ["ab","cd"] 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= access_times.length &lt;= 100</code></li>
	<li><code>access_times[i].length == 2</code></li>
	<li><code>1 &lt;= access_times[i][0].length &lt;= 10</code></li>
	<li><code>access_times[i][0]</code> 仅由小写英文字母组成。</li>
	<li><code>access_times[i][1].length == 4</code></li>
	<li><code>access_times[i][1]</code> 采用24小时制表示时间。</li>
	<li><code>access_times[i][1]</code> 仅由数字 <code>'0'</code> 到 <code>'9'</code> 组成。</li>
</ul>

## 解法

### 方法一：哈希表 + 排序

我们用一个哈希表 $d$ 来存储每个员工的所有访问时间，其中键为员工的姓名，值为一个整数数组，表示该员工的所有访问时间，该时间为从当天 00:00 开始的分钟数。

对于每个员工，我们将其所有访问时间按照从小到大的顺序进行排序。然后我们遍历该员工的所有访问时间，如果存在连续的三个访问时间 $t_1, t_2, t_3$，满足 $t_3 - t_1 < 60$，则该员工为高访问员工，我们将其姓名加入答案数组中。

最后，返回答案数组即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为访问记录的数量。

<!-- tabs:start -->

```python
class Solution:
    def findHighAccessEmployees(self, access_times: List[List[str]]) -> List[str]:
        d = defaultdict(list)
        for name, t in access_times:
            d[name].append(int(t[:2]) * 60 + int(t[2:]))
        ans = []
        for name, ts in d.items():
            ts.sort()
            if any(ts[i] - ts[i - 2] < 60 for i in range(2, len(ts))):
                ans.append(name)
        return ans
```

```java
class Solution {
    public List<String> findHighAccessEmployees(List<List<String>> access_times) {
        Map<String, List<Integer>> d = new HashMap<>();
        for (var e : access_times) {
            String name = e.get(0), s = e.get(1);
            int t = Integer.valueOf(s.substring(0, 2)) * 60 + Integer.valueOf(s.substring(2));
            d.computeIfAbsent(name, k -> new ArrayList<>()).add(t);
        }
        List<String> ans = new ArrayList<>();
        for (var e : d.entrySet()) {
            String name = e.getKey();
            var ts = e.getValue();
            Collections.sort(ts);
            for (int i = 2; i < ts.size(); ++i) {
                if (ts.get(i) - ts.get(i - 2) < 60) {
                    ans.add(name);
                    break;
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
    vector<string> findHighAccessEmployees(vector<vector<string>>& access_times) {
        unordered_map<string, vector<int>> d;
        for (auto& e : access_times) {
            auto name = e[0];
            auto s = e[1];
            int t = stoi(s.substr(0, 2)) * 60 + stoi(s.substr(2, 2));
            d[name].emplace_back(t);
        }
        vector<string> ans;
        for (auto& [name, ts] : d) {
            sort(ts.begin(), ts.end());
            for (int i = 2; i < ts.size(); ++i) {
                if (ts[i] - ts[i - 2] < 60) {
                    ans.emplace_back(name);
                    break;
                }
            }
        }
        return ans;
    }
};
```

```go
func findHighAccessEmployees(access_times [][]string) (ans []string) {
	d := map[string][]int{}
	for _, e := range access_times {
		name, s := e[0], e[1]
		h, _ := strconv.Atoi(s[:2])
		m, _ := strconv.Atoi(s[2:])
		t := h*60 + m
		d[name] = append(d[name], t)
	}
	for name, ts := range d {
		sort.Ints(ts)
		for i := 2; i < len(ts); i++ {
			if ts[i]-ts[i-2] < 60 {
				ans = append(ans, name)
				break
			}
		}
	}
	return
}
```

```ts
function findHighAccessEmployees(access_times: string[][]): string[] {
    const d: Map<string, number[]> = new Map();
    for (const [name, s] of access_times) {
        const h = parseInt(s.slice(0, 2), 10);
        const m = parseInt(s.slice(2), 10);
        const t = h * 60 + m;
        if (!d.has(name)) {
            d.set(name, []);
        }
        d.get(name)!.push(t);
    }
    const ans: string[] = [];
    for (const [name, ts] of d) {
        ts.sort((a, b) => a - b);
        for (let i = 2; i < ts.length; ++i) {
            if (ts[i] - ts[i - 2] < 60) {
                ans.push(name);
                break;
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
