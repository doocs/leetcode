# [2512. Reward Top K Students](https://leetcode.com/problems/reward-top-k-students)

[中文文档](/solution/2500-2599/2512.Reward%20Top%20K%20Students/README.md)

## Description

<p>You are given two string arrays <code>positive_feedback</code> and <code>negative_feedback</code>, containing the words denoting positive and negative feedback, respectively. Note that <strong>no</strong> word is both positive and negative.</p>

<p>Initially every student has <code>0</code> points. Each positive word in a feedback report <strong>increases</strong> the points of a student by <code>3</code>, whereas each negative word <strong>decreases</strong> the points by <code>1</code>.</p>

<p>You are given <code>n</code> feedback reports, represented by a <strong>0-indexed</strong> string array <code>report</code>&nbsp;and a <strong>0-indexed</strong> integer array <code>student_id</code>, where <code>student_id[i]</code> represents the ID of the student who has received the feedback report <code>report[i]</code>. The ID of each student is <strong>unique</strong>.</p>

<p>Given an integer <code>k</code>, return <em>the top </em><code>k</code><em> students after ranking them in <strong>non-increasing</strong> order by their points</em>. In case more than one student has the same points, the one with the lower ID ranks higher.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> positive_feedback = [&quot;smart&quot;,&quot;brilliant&quot;,&quot;studious&quot;], negative_feedback = [&quot;not&quot;], report = [&quot;this student is studious&quot;,&quot;the student is smart&quot;], student_id = [1,2], k = 2
<strong>Output:</strong> [1,2]
<strong>Explanation:</strong> 
Both the students have 1 positive feedback and 3 points but since student 1 has a lower ID he ranks higher.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> positive_feedback = [&quot;smart&quot;,&quot;brilliant&quot;,&quot;studious&quot;], negative_feedback = [&quot;not&quot;], report = [&quot;this student is not studious&quot;,&quot;the student is smart&quot;], student_id = [1,2], k = 2
<strong>Output:</strong> [2,1]
<strong>Explanation:</strong> 
- The student with ID 1 has 1 positive feedback and 1 negative feedback, so he has 3-1=2 points. 
- The student with ID 2 has 1 positive feedback, so he has 3 points. 
Since student 2 has more points, [2,1] is returned.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= positive_feedback.length, negative_feedback.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= positive_feedback[i].length, negative_feedback[j].length &lt;= 100</code></li>
	<li>Both <code>positive_feedback[i]</code> and <code>negative_feedback[j]</code> consists of lowercase English letters.</li>
	<li>No word is present in both <code>positive_feedback</code> and <code>negative_feedback</code>.</li>
	<li><code>n == report.length == student_id.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>report[i]</code> consists of lowercase English letters and spaces <code>&#39; &#39;</code>.</li>
	<li>There is a single space between consecutive words of <code>report[i]</code>.</li>
	<li><code>1 &lt;= report[i].length &lt;= 100</code></li>
	<li><code>1 &lt;= student_id[i] &lt;= 10<sup>9</sup></code></li>
	<li>All the values of <code>student_id[i]</code> are <strong>unique</strong>.</li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def topStudents(
        self,
        positive_feedback: List[str],
        negative_feedback: List[str],
        report: List[str],
        student_id: List[int],
        k: int,
    ) -> List[int]:
        ps = set(positive_feedback)
        ns = set(negative_feedback)
        arr = []
        for sid, r in zip(student_id, report):
            t = 0
            for w in r.split():
                if w in ps:
                    t += 3
                elif w in ns:
                    t -= 1
            arr.append((t, sid))
        arr.sort(key=lambda x: (-x[0], x[1]))
        return [v[1] for v in arr[:k]]
```

### **Java**

```java
class Solution {
    public List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback,
        String[] report, int[] student_id, int k) {
        Set<String> ps = new HashSet<>();
        Set<String> ns = new HashSet<>();
        for (var s : positive_feedback) {
            ps.add(s);
        }
        for (var s : negative_feedback) {
            ns.add(s);
        }
        int n = report.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; ++i) {
            int sid = student_id[i];
            int t = 0;
            for (var s : report[i].split(" ")) {
                if (ps.contains(s)) {
                    t += 3;
                } else if (ns.contains(s)) {
                    t -= 1;
                }
            }
            arr[i] = new int[] {t, sid};
        }
        Arrays.sort(arr, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < k; ++i) {
            ans.add(arr[i][1]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> topStudents(vector<string>& positive_feedback, vector<string>& negative_feedback, vector<string>& report, vector<int>& student_id, int k) {
        unordered_set<string> ps(positive_feedback.begin(), positive_feedback.end());
        unordered_set<string> ns(negative_feedback.begin(), negative_feedback.end());
        vector<pair<int, int>> arr;
        int n = report.size();
        for (int i = 0; i < n; ++i) {
            int sid = student_id[i];
            vector<string> ws = split(report[i], ' ');
            int t = 0;
            for (auto& w : ws) {
                if (ps.count(w)) {
                    t += 3;
                } else if (ns.count(w)) {
                    t -= 1;
                }
            }
            arr.push_back({-t, sid});
        }
        sort(arr.begin(), arr.end());
        vector<int> ans;
        for (int i = 0; i < k; ++i) {
            ans.emplace_back(arr[i].second);
        }
        return ans;
    }

    vector<string> split(string& s, char delim) {
        stringstream ss(s);
        string item;
        vector<string> res;
        while (getline(ss, item, delim)) {
            res.emplace_back(item);
        }
        return res;
    }
};
```

### **Go**

```go
func topStudents(positive_feedback []string, negative_feedback []string, report []string, student_id []int, k int) (ans []int) {
	ps := map[string]bool{}
	ns := map[string]bool{}
	for _, s := range positive_feedback {
		ps[s] = true
	}
	for _, s := range negative_feedback {
		ns[s] = true
	}
	arr := [][2]int{}
	for i, sid := range student_id {
		t := 0
		for _, w := range strings.Split(report[i], " ") {
			if ps[w] {
				t += 3
			} else if ns[w] {
				t -= 1
			}
		}
		arr = append(arr, [2]int{t, sid})
	}
	sort.Slice(arr, func(i, j int) bool { return arr[i][0] > arr[j][0] || (arr[i][0] == arr[j][0] && arr[i][1] < arr[j][1]) })
	for _, v := range arr[:k] {
		ans = append(ans, v[1])
	}
	return
}
```

### **TypeScript**

```ts
function topStudents(
    positive_feedback: string[],
    negative_feedback: string[],
    report: string[],
    student_id: number[],
    k: number,
): number[] {
    const n = student_id.length;
    const map = new Map<number, number>();
    const ps = new Set(positive_feedback);
    const ns = new Set(negative_feedback);
    for (let i = 0; i < n; i++) {
        map.set(
            student_id[i],
            report[i].split(' ').reduce((r, s) => {
                if (ps.has(s)) {
                    return r + 3;
                }
                if (ns.has(s)) {
                    return r - 1;
                }
                return r;
            }, 0),
        );
    }
    return [...map.entries()]
        .sort((a, b) => {
            if (a[1] === b[1]) {
                return a[0] - b[0];
            }
            return b[1] - a[1];
        })
        .map(v => v[0])
        .slice(0, k);
}
```

### **Rust**

```rust
use std::collections::{HashMap, HashSet};
impl Solution {
    pub fn top_students(
        positive_feedback: Vec<String>,
        negative_feedback: Vec<String>,
        report: Vec<String>,
        student_id: Vec<i32>,
        k: i32,
    ) -> Vec<i32> {
        let n = student_id.len();
        let ps = positive_feedback.iter().collect::<HashSet<&String>>();
        let ns = negative_feedback.iter().collect::<HashSet<&String>>();
        let mut map = HashMap::new();
        for i in 0..n {
            let id = student_id[i];
            let mut count = 0;
            for s in report[i].split(' ') {
                let s = &s.to_string();
                if ps.contains(s) {
                    count += 3;
                } else if ns.contains(s) {
                    count -= 1;
                }
            }
            map.insert(id, count);
        }
        let mut t = map.into_iter().collect::<Vec<(i32, i32)>>();
        t.sort_by(|a, b| {
            if a.1 == b.1 {
                return a.0.cmp(&b.0);
            }
            b.1.cmp(&a.1)
        });
        t.iter().map(|v| v.0).collect::<Vec<i32>>()[0..k as usize].to_vec()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
