# [388. 文件的最长绝对路径](https://leetcode.cn/problems/longest-absolute-file-path)

[English Version](/solution/0300-0399/0388.Longest%20Absolute%20File%20Path/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>假设有一个同时存储文件和目录的文件系统。下图展示了文件系统的一个示例：</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0388.Longest%20Absolute%20File%20Path/images/mdir.jpg" style="height: 142px; width: 300px;" /></p>

<p>这里将 <code>dir</code> 作为根目录中的唯一目录。<code>dir</code> 包含两个子目录 <code>subdir1</code> 和 <code>subdir2</code> 。<code>subdir1</code> 包含文件 <code>file1.ext</code> 和子目录 <code>subsubdir1</code>；<code>subdir2</code> 包含子目录 <code>subsubdir2</code>，该子目录下包含文件 <code>file2.ext</code> 。</p>

<p>在文本格式中，如下所示(⟶表示制表符)：</p>

<pre>
dir
⟶ subdir1
⟶ ⟶ file1.ext
⟶ ⟶ subsubdir1
⟶ subdir2
⟶ ⟶ subsubdir2
⟶ ⟶ ⟶ file2.ext
</pre>

<p>如果是代码表示，上面的文件系统可以写为 <code>"dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"</code> 。<code>'\n'</code> 和 <code>'\t'</code> 分别是换行符和制表符。</p>

<p>文件系统中的每个文件和文件夹都有一个唯一的 <strong>绝对路径</strong> ，即必须打开才能到达文件/目录所在位置的目录顺序，所有路径用 <code>'/'</code> 连接。上面例子中，指向 <code>file2.ext</code> 的 <strong>绝对路径</strong> 是 <code>"dir/subdir2/subsubdir2/file2.ext"</code> 。每个目录名由字母、数字和/或空格组成，每个文件名遵循 <code>name.extension</code> 的格式，其中<meta charset="UTF-8" />&nbsp;<code>name</code>&nbsp;和<meta charset="UTF-8" />&nbsp;<code>extension</code>由字母、数字和/或空格组成。</p>

<p>给定一个以上述格式表示文件系统的字符串 <code>input</code> ，返回文件系统中&nbsp;<em>指向&nbsp;<strong>文件</strong>&nbsp;的 <strong>最长绝对路径</strong> 的长度</em>&nbsp;。 如果系统中没有文件，返回&nbsp;<code>0</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0388.Longest%20Absolute%20File%20Path/images/dir1.jpg" style="height: 101px; width: 200px;" />
<pre>
<strong>输入：</strong>input = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"
<strong>输出：</strong>20
<strong>解释：</strong>只有一个文件，绝对路径为 "dir/subdir2/file.ext" ，路径长度 20
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0388.Longest%20Absolute%20File%20Path/images/dir2.jpg" style="height: 151px; width: 300px;" />
<pre>
<strong>输入：</strong>input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"
<strong>输出：</strong>32
<strong>解释：</strong>存在两个文件：
"dir/subdir1/file1.ext" ，路径长度 21
"dir/subdir2/subsubdir2/file2.ext" ，路径长度 32
返回 32 ，因为这是最长的路径</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>input = "a"
<strong>输出：</strong>0
<strong>解释：</strong>不存在任何文件</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>input = "file1.txt\nfile2.txt\nlongfile.txt"
<strong>输出：</strong>12
<strong>解释：</strong>根目录下有 3 个文件。
因为根目录中任何东西的绝对路径只是名称本身，所以答案是 "longfile.txt" ，路径长度为 12
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= input.length &lt;= 10<sup>4</sup></code></li>
	<li><code>input</code> 可能包含小写或大写的英文字母，一个换行符 <code>'\n'</code>，一个制表符 <code>'\t'</code>，一个点 <code>'.'</code>，一个空格 <code>' '</code>，和数字。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

遍历文件系统的时候需要在各个目录间切换，在实际的 Linux 中，有 `pushd` 和 `popd` 命令，本题可以使用栈模拟这一过程

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
