# [609. Find Duplicate File in System](https://leetcode.com/problems/find-duplicate-file-in-system)

## Description
<p>Given a list of directory info including directory path, and all the files with contents in this directory, you need to find out all the groups of duplicate files in the file system in terms of their paths.</p>

<p>A group of duplicate files consists of at least <b>two</b> files that have exactly the same content.</p>

<p>A single directory info string in the <b>input</b> list has the following format:</p>

<p><code>&quot;root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)&quot;</code></p>

<p>It means there are <b>n</b> files (<code>f1.txt</code>, <code>f2.txt</code> ... <code>fn.txt</code> with content <code>f1_content</code>, <code>f2_content</code> ... <code>fn_content</code>, respectively) in directory <code>root/d1/d2/.../dm</code>. Note that n &gt;= 1 and m &gt;= 0. If m = 0, it means the directory is just the root directory.</p>

<p>The <b>output</b> is a list of group of duplicate file paths. For each group, it contains all the file paths of the files that have the same content. A file path is a string that has the following format:</p>

<p><code>&quot;directory_path/file_name.txt&quot;</code></p>

<p><b>Example 1:</b></p>

<pre>
<b>Input:</b>
[&quot;root/a 1.txt(abcd) 2.txt(efgh)&quot;, &quot;root/c 3.txt(abcd)&quot;, &quot;root/c/d 4.txt(efgh)&quot;, &quot;root 4.txt(efgh)&quot;]
<b>Output:</b>  
[[&quot;root/a/2.txt&quot;,&quot;root/c/d/4.txt&quot;,&quot;root/4.txt&quot;],[&quot;root/a/1.txt&quot;,&quot;root/c/3.txt&quot;]]
</pre>

<p>&nbsp;</p>

<p><b>Note:</b></p>

<ol>
	<li>No order is required for the final output.</li>
	<li>You may assume the directory name, file name and file content only has letters and digits, and the length of file content is in the range of [1,50].</li>
	<li>The number of files given is in the range of [1,20000].</li>
	<li>You may assume no files or directories share the same name in the same directory.</li>
	<li>You may assume each given directory info represents a unique directory. Directory path and file info are separated by a single blank space.</li>
</ol>

<p>&nbsp;</p>
<b>Follow-up beyond contest:</b>

<ol>
	<li>Imagine you are given a real file system, how will you search files? DFS or BFS?</li>
	<li>If the file content is very large (GB level), how will you modify your solution?</li>
	<li>If you can only read the file by 1kb each time, how will you modify your solution?</li>
	<li>What is the time complexity of your modified solution? What is the most time-consuming part and memory consuming part of it? How to optimize?</li>
	<li>How to make sure the duplicated files you find are not false positive?</li>
</ol>



## Solutions


### Python3

```python

```

### Java

```java

```

### ...
```

```
