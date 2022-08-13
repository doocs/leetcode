# [165. Compare Version Numbers](https://leetcode.com/problems/compare-version-numbers)

[中文文档](/solution/0100-0199/0165.Compare%20Version%20Numbers/README.md)

## Description

<p>Given two version numbers,&nbsp;<code>version1</code> and <code>version2</code>, compare them.</p>

<ul>
</ul>

<p>Version numbers consist of <strong>one or more revisions</strong> joined by a dot&nbsp;<code>&#39;.&#39;</code>. Each revision&nbsp;consists of <strong>digits</strong>&nbsp;and may contain leading <strong>zeros</strong>. Every revision contains <strong>at least one character</strong>. Revisions are <strong>0-indexed from left to right</strong>, with the leftmost revision being revision 0, the next revision being revision 1, and so on. For example&nbsp;<code>2.5.33</code>&nbsp;and&nbsp;<code>0.1</code>&nbsp;are valid version numbers.</p>

<p>To compare version numbers, compare their revisions in <strong>left-to-right order</strong>. Revisions are compared using their&nbsp;<strong>integer value ignoring any leading zeros</strong>. This means that revisions&nbsp;<code>1</code>&nbsp;and&nbsp;<code>001</code>&nbsp;are considered&nbsp;<strong>equal</strong>. If a version number does not specify a revision at an index, then&nbsp;<strong>treat the revision as&nbsp;<code>0</code></strong>. For example, version&nbsp;<code>1.0</code> is less than version&nbsp;<code>1.1</code>&nbsp;because their revision 0s are the same, but their revision 1s are&nbsp;<code>0</code>&nbsp;and&nbsp;<code>1</code>&nbsp;respectively, and&nbsp;<code>0 &lt; 1</code>.</p>

<p><em>Return the following:</em></p>

<ul>
	<li>If <code>version1 &lt; version2</code>, return <code>-1</code>.</li>
	<li>If <code>version1 &gt; version2</code>, return <code>1</code>.</li>
	<li>Otherwise, return <code>0</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> version1 = &quot;1.01&quot;, version2 = &quot;1.001&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> Ignoring leading zeroes, both &quot;01&quot; and &quot;001&quot; represent the same integer &quot;1&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> version1 = &quot;1.0&quot;, version2 = &quot;1.0.0&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> version1 does not specify revision 2, which means it is treated as &quot;0&quot;.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> version1 = &quot;0.1&quot;, version2 = &quot;1.1&quot;
<strong>Output:</strong> -1
<strong>Explanation:</strong> version1&#39;s revision 0 is &quot;0&quot;, while version2&#39;s revision 0 is &quot;1&quot;. 0 &lt; 1, so version1 &lt; version2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= version1.length, version2.length &lt;= 500</code></li>
	<li><code>version1</code> and <code>version2</code>&nbsp;only contain digits and <code>&#39;.&#39;</code>.</li>
	<li><code>version1</code> and <code>version2</code>&nbsp;<strong>are valid version numbers</strong>.</li>
	<li>All the given revisions in&nbsp;<code>version1</code> and <code>version2</code>&nbsp;can be stored in&nbsp;a&nbsp;<strong>32-bit integer</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def compareVersion(self, version1: str, version2: str) -> int:
        i, j, m, n = 0, 0, len(version1), len(version2)
        while i < m or j < n:
            a = b = 0
            while i < m and version1[i] != '.':
                a = a * 10 + int(version1[i])
                i += 1
            while j < n and version2[j] != '.':
                b = b * 10 + int(version2[j])
                j += 1
            if a != b:
                return -1 if a < b else 1
            i += 1
            j += 1
        return 0
```

### **Java**

```java
class Solution {
    public int compareVersion(String version1, String version2) {
        for (int i = 0, j = 0; i < version1.length() || j < version2.length(); ++i, ++j) {
            int a = 0, b = 0;
            while (i < version1.length() && version1.charAt(i) != '.') {
                a = a * 10 + version1.charAt(i++) - '0';
            }
            while (j < version2.length() && version2.charAt(j) != '.') {
                b = b * 10 + version2.charAt(j++) - '0';
            }
            if (a != b) {
                return a < b ? -1 : 1;
            }
        }
        return 0;
    }
}
```

### **TypeScript**

```ts
function compareVersion(version1: string, version2: string): number {
    let v1 = version1.split('.'),
        v2 = version2.split('.');
    for (let i = 0; i < Math.max(v1.length, v2.length); i++) {
        let c1 = Number(v1[i] || 0),
            c2 = Number(v2[i] || 0);
        if (c1 > c2) return 1;
        if (c1 < c2) return -1;
    }
    return 0;
}
```

### **C++**

```cpp
class Solution {
public:
    int compareVersion(string version1, string version2) {
        for (int i = 0, j = 0; i < version1.size() || j < version2.size(); ++i, ++j) {
            int a = 0, b = 0;
            while (i < version1.size() && version1[i] != '.')
                a = a * 10 + version1[i++] - '0';
            while (j < version2.size() && version2[j] != '.')
                b = b * 10 + version2[j++] - '0';
            if (a != b)
                return a < b ? -1 : 1;
        }
        return 0;
    }
};
```

### **Go**

```go
func compareVersion(version1 string, version2 string) int {
	for i, j := 0, 0; i < len(version1) || j < len(version2); i, j = i+1, j+1 {
		a, b := 0, 0
		for i < len(version1) && version1[i] != '.' {
			a = a*10 + int(version1[i]-'0')
			i++
		}
		for j < len(version2) && version2[j] != '.' {
			b = b*10 + int(version2[j]-'0')
			j++
		}
		if a < b {
			return -1
		}
		if a > b {
			return 1
		}
	}
	return 0
}
```

### **...**

```

```

<!-- tabs:end -->
