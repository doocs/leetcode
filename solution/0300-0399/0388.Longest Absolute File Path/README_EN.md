# [388. Longest Absolute File Path](https://leetcode.com/problems/longest-absolute-file-path)

[中文文档](/solution/0300-0399/0388.Longest%20Absolute%20File%20Path/README.md)

## Description

<p>Suppose we have a file system that stores both files and directories. An example of one system is represented in the following picture:</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0388.Longest%20Absolute%20File%20Path/images/mdir.jpg" style="width: 681px; height: 322px;" /></p>

<p>Here, we have <code>dir</code> as the only directory in the root. <code>dir</code> contains two subdirectories, <code>subdir1</code> and <code>subdir2</code>. <code>subdir1</code> contains a file <code>file1.ext</code> and subdirectory <code>subsubdir1</code>. <code>subdir2</code> contains a subdirectory <code>subsubdir2</code>, which contains a file <code>file2.ext</code>.</p>

<p>In text form, it looks like this (with ⟶ representing the tab character):</p>

<pre>
dir
⟶ subdir1
⟶ ⟶ file1.ext
⟶ ⟶ subsubdir1
⟶ subdir2
⟶ ⟶ subsubdir2
⟶ ⟶ ⟶ file2.ext
</pre>

<p>If we were to write this representation in code, it will look like this: <code>&quot;dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext&quot;</code>. Note that the <code>&#39;\n&#39;</code> and <code>&#39;\t&#39;</code> are the new-line and tab characters.</p>

<p>Every file and directory has a unique <strong>absolute path</strong> in the file system, which is the order of directories that must be opened to reach the file/directory itself, all concatenated by <code>&#39;/&#39;s</code>. Using the above example, the <strong>absolute path</strong> to <code>file2.ext</code> is <code>&quot;dir/subdir2/subsubdir2/file2.ext&quot;</code>. Each directory name consists of letters, digits, and/or spaces. Each file name is of the form <code>name.extension</code>, where <code>name</code> and <code>extension</code> consist of letters, digits, and/or spaces.</p>

<p>Given a string <code>input</code> representing the file system in the explained format, return <em>the length of the <strong>longest absolute path</strong> to a <strong>file</strong> in the abstracted file system</em>. If there is no file in the system, return <code>0</code>.</p>

<p><strong>Note</strong> that the testcases are generated such that the file system is valid and no file or directory name has length 0.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0388.Longest%20Absolute%20File%20Path/images/dir1.jpg" style="width: 401px; height: 202px;" />
<pre>
<strong>Input:</strong> input = &quot;dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext&quot;
<strong>Output:</strong> 20
<strong>Explanation:</strong> We have only one file, and the absolute path is &quot;dir/subdir2/file.ext&quot; of length 20.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0388.Longest%20Absolute%20File%20Path/images/dir2.jpg" style="width: 641px; height: 322px;" />
<pre>
<strong>Input:</strong> input = &quot;dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext&quot;
<strong>Output:</strong> 32
<strong>Explanation:</strong> We have two files:
&quot;dir/subdir1/file1.ext&quot; of length 21
&quot;dir/subdir2/subsubdir2/file2.ext&quot; of length 32.
We return 32 since it is the longest absolute path to a file.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> input = &quot;a&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> We do not have any files, just a single directory named &quot;a&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= input.length &lt;= 10<sup>4</sup></code></li>
	<li><code>input</code> may contain lowercase or uppercase English letters, a new line character <code>&#39;\n&#39;</code>, a tab character <code>&#39;\t&#39;</code>, a dot <code>&#39;.&#39;</code>, a space <code>&#39; &#39;</code>, and digits.</li>
	<li>All file and directory names have <strong>positive</strong> length.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def lengthLongestPath(self, input: str) -> int:
        i, n = 0, len(input)
        ans = 0
        stk = []
        while i < n:
            ident = 0
            while input[i] == '\t':
                ident += 1
                i += 1

            cur, isFile = 0, False
            while i < n and input[i] != '\n':
                cur += 1
                if input[i] == '.':
                    isFile = True
                i += 1
            i += 1

            # popd
            while len(stk) > 0 and len(stk) > ident:
                stk.pop()

            if len(stk) > 0:
                cur += stk[-1] + 1

            # pushd
            if not isFile:
                stk.append(cur)
                continue

            ans = max(ans, cur)

        return ans
```

### **Java**

```java
class Solution {
    public int lengthLongestPath(String input) {
        int i = 0;
        int n = input.length();
        int ans = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        while (i < n) {
            int ident = 0;
            for (; input.charAt(i) == '\t'; i++) {
                ident++;
            }

            int cur = 0;
            boolean isFile = false;
            for (; i < n && input.charAt(i) != '\n'; i++) {
                cur++;
                if (input.charAt(i) == '.') {
                    isFile = true;
                }
            }
            i++;

            // popd
            while (!stack.isEmpty() && stack.size() > ident) {
                stack.pop();
            }

            if (stack.size() > 0) {
                cur += stack.peek() + 1;
            }

            // pushd
            if (!isFile) {
                stack.push(cur);
                continue;
            }

            ans = Math.max(ans, cur);
        }
        return ans;
    }
}
```

### **Go**

```go
func lengthLongestPath(input string) int {
	i, n := 0, len(input)
	ans := 0
	var stk []int
	for i < n {
		ident := 0
		for ; input[i] == '\t'; i++ {
			ident++
		}

		cur, isFile := 0, false
		for ; i < n && input[i] != '\n'; i++ {
			cur++
			if input[i] == '.' {
				isFile = true
			}
		}
		i++

		// popd
		for len(stk) > 0 && len(stk) > ident {
			stk = stk[:len(stk)-1]
		}

		if len(stk) > 0 {
			cur += stk[len(stk)-1] + 1
		}

		// pushd
		if !isFile {
			stk = append(stk, cur)
			continue
		}

		ans = max(ans, cur)
	}
	return ans
}

func max(x, y int) int {
	if x > y {
		return x
	}
	return y
}
```

### **C++**

```cpp
class Solution {
public:
    int lengthLongestPath(string input) {
        int i = 0, n = input.size();
        int ans = 0;
        stack<int> stk;
        while (i < n) {
            int ident = 0;
            for (; input[i] == '\t'; ++i) {
                ++ident;
            }

            int cur = 0;
            bool isFile = false;
            for (; i < n && input[i] != '\n'; ++i) {
                ++cur;
                if (input[i] == '.') {
                    isFile = true;
                }
            }
            ++i;

            // popd
            while (!stk.empty() && stk.size() > ident) {
                stk.pop();
            }

            if (stk.size() > 0) {
                cur += stk.top() + 1;
            }

            // pushd
            if (!isFile) {
                stk.push(cur);
                continue;
            }

            ans = max(ans, cur);
        }
        return ans;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
