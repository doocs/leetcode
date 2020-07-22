# [388. 文件的最长绝对路径](https://leetcode-cn.com/problems/longest-absolute-file-path)

## 题目描述
<!-- 这里写题目描述 -->
<p>假设我们以下述方式将我们的文件系统抽象成一个字符串:</p>

<p>字符串&nbsp;<code>&quot;dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext&quot;</code> 表示:</p>

<pre>
dir
    subdir1
    subdir2
        file.ext
</pre>

<p>目录&nbsp;<code>dir</code> 包含一个空的子目录&nbsp;<code>subdir1</code> 和一个包含一个文件&nbsp;<code>file.ext</code>&nbsp;的子目录&nbsp;<code>subdir2</code> 。</p>

<p>字符串&nbsp;<code>&quot;dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext&quot;</code> 表示:</p>

<pre>
dir
    subdir1
        file1.ext
        subsubdir1
    subdir2
        subsubdir2
            file2.ext
</pre>

<p>目录&nbsp;<code>dir</code> 包含两个子目录 <code>subdir1</code> 和&nbsp;<code>subdir2</code>。&nbsp;<code>subdir1</code> 包含一个文件&nbsp;<code>file1.ext</code> 和一个空的二级子目录 <code>subsubdir1</code>。<code>subdir2</code> 包含一个二级子目录&nbsp;<code>subsubdir2</code> ，其中包含一个文件&nbsp;<code>file2.ext</code>。</p>

<p>我们致力于寻找我们文件系统中文件的最长 (按字符的数量统计) 绝对路径。例如，在上述的第二个例子中，最长路径为&nbsp;<code>&quot;dir/subdir2/subsubdir2/file2.ext&quot;</code>，其长度为&nbsp;<code>32</code> (不包含双引号)。</p>

<p>给定一个以上述格式表示文件系统的字符串，返回文件系统中文件的最长绝对路径的长度。 如果系统中没有文件，返回&nbsp;<code>0</code>。</p>

<p><strong>说明:</strong></p>

<ul>
	<li>文件名至少存在一个&nbsp;<code>.</code> 和一个扩展名。</li>
	<li>目录或者子目录的名字不能包含&nbsp;<code>.</code>。</li>
</ul>

<p>要求时间复杂度为&nbsp;<code>O(n)</code>&nbsp;，其中&nbsp;<code>n</code> 是输入字符串的大小。</p>

<p>请注意，如果存在路径&nbsp;<code>aaaaaaaaaaaaaaaaaaaaa/sth.png</code>&nbsp;的话，那么&nbsp;&nbsp;<code>a/aa/aaa/file1.txt</code>&nbsp;就不是一个最长的路径。</p>



## 解法
<!-- 这里可写通用的实现逻辑 -->


<!-- tabs:start -->

### **Python3**
<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**
<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **...**
```

```

<!-- tabs:end -->