package main

import (
	"io/ioutil"
	"os"
	"strings"
)

func main() {
	myfolder := "D:/workspace/bluesword/leetcode/solution"
	files, _ := ioutil.ReadDir(myfolder)
	for _, file := range files {
		if file.IsDir() {
			name := file.Name()
			split := strings.Split(name, ".")
			os.Rename(myfolder+"/"+name, myfolder+"/"+"0"+split[0]+"."+strings.TrimSpace(split[1]))
		} else {
			continue
		}
	}

	/*file := "C:\\\\log\\\\2013.log"                        //源文件路径
	err := os.Rename(file, "C:\\\\log\\\\install.txt")     //重命名 C:\\log\\2013.log 文件为install.txt
	if err != nil {
		//如果重命名文件失败,则输出错误 file rename Error!
		fmt.Println("file rename Error!")
		//打印错误详细信息
		fmt.Printf("%s", err)
	} else {
		//如果文件重命名成功,则输出 file rename OK!
		fmt.Println("file rename OK!")
	}*/
}
