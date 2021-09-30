# [1436. Destination City](https://leetcode.com/problems/destination-city)

[中文文档](/solution/1400-1499/1436.Destination%20City/README.md)

## Description

<p>You are given the array <code>paths</code>, where <code>paths[i] = [cityA<sub>i</sub>, cityB<sub>i</sub>]</code> means there exists a direct path going from <code>cityA<sub>i</sub></code> to <code>cityB<sub>i</sub></code>. <em>Return the destination city, that is, the city without any path outgoing to another city.</em></p>

<p>It is guaranteed that the graph of paths forms a line without any loop, therefore, there will be exactly one destination city.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> paths = [[&quot;London&quot;,&quot;New York&quot;],[&quot;New York&quot;,&quot;Lima&quot;],[&quot;Lima&quot;,&quot;Sao Paulo&quot;]]
<strong>Output:</strong> &quot;Sao Paulo&quot; 
<strong>Explanation:</strong> Starting at &quot;London&quot; city you will reach &quot;Sao Paulo&quot; city which is the destination city. Your trip consist of: &quot;London&quot; -&gt; &quot;New York&quot; -&gt; &quot;Lima&quot; -&gt; &quot;Sao Paulo&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> paths = [[&quot;B&quot;,&quot;C&quot;],[&quot;D&quot;,&quot;B&quot;],[&quot;C&quot;,&quot;A&quot;]]
<strong>Output:</strong> &quot;A&quot;
<strong>Explanation:</strong> All possible trips are:&nbsp;
&quot;D&quot; -&gt; &quot;B&quot; -&gt; &quot;C&quot; -&gt; &quot;A&quot;.&nbsp;
&quot;B&quot; -&gt; &quot;C&quot; -&gt; &quot;A&quot;.&nbsp;
&quot;C&quot; -&gt; &quot;A&quot;.&nbsp;
&quot;A&quot;.&nbsp;
Clearly the destination city is &quot;A&quot;.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> paths = [[&quot;A&quot;,&quot;Z&quot;]]
<strong>Output:</strong> &quot;Z&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= paths.length &lt;= 100</code></li>
	<li><code>paths[i].length == 2</code></li>
	<li><code>1 &lt;= cityA<sub>i</sub>.length, cityB<sub>i</sub>.length &lt;= 10</code></li>
	<li><code>cityA<sub>i</sub> != cityB<sub>i</sub></code></li>
	<li>All strings consist of lowercase and uppercase English letters and the space character.</li>
</ul>


## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def destCity(self, paths: List[List[str]]) -> str:
        mp = {a: b for a, b in paths}
        a =  paths[0][0]
        while mp.get(a):
            a = mp[a]
        return a
```

### **Java**

```java
class Solution {
    public String destCity(List<List<String>> paths) {
        Map<String, String> mp = new HashMap<>();
        for (List<String> path : paths) {
            mp.put(path.get(0), path.get(1));
        }
        String a = paths.get(0).get(0);
        while (mp.get(a) != null) {
            a = mp.get(a);
        }
        return a;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string destCity(vector<vector<string>>& paths) {
        unordered_map<string, string> mp;
        for (auto& path : paths) mp[path[0]] = path[1];
        string a = paths[0][0];
        while (mp.find(a) != mp.end()) a = mp[a];
        return a;
    }
};
```

### **Go**

```go
func destCity(paths [][]string) string {
	mp := make(map[string]string)
	for _, path := range paths {
		mp[path[0]] = path[1]
	}
	a := paths[0][0]
	for true {
		if _, ok := mp[a]; !ok {
			return a
		}
		a = mp[a]
	}
	return ""
}
```

### **...**

```

```

<!-- tabs:end -->
