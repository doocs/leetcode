import os.path
import re

path = os.getcwd()


def format():
    for suffix in ['md', 'js', 'ts']:
        command = f'prettier --write "**/*.{suffix}"'
        os.system(command)


def format_cpp():
    for root, _, files in os.walk(path):
        for name in files:
            if name.endswith('.cpp') or name.endswith('.c'):
                local_path = root + '/' + name
                command = f'clang-format -i --style=file "{local_path}"'
                print(command)
                os.system(command)

    for root, _, files in os.walk(path):
        for name in files:
            if name.endswith('.md'):
                p1 = root + '/' + name
                with open(p1, 'r', encoding='utf-8') as f:
                    content = f.read()
                    x = content
                res = re.search('```cpp(.*?)```', content, re.S)
                if not res:
                    continue
                print(p1)
                res = res.group(1)
                p2 = root + "/tmp.cpp"
                with open(p2, 'w', encoding='utf-8') as f:
                    f.write(res)
                command = f'clang-format -i --style=file "{p2}"'
                os.system(command)
                with open(p2, 'r', encoding='utf-8') as f:
                    content = f.read()
                content = x.replace(res, content)
                with open(p1, 'w', encoding='utf-8') as f:
                    f.write(content)
                os.remove(p2)


def format_py():
    command = 'black -S .'
    os.system(command)

    for root, _, files in os.walk(path):
        for name in files:
            if name.endswith('.md'):
                p1 = root + '/' + name
                with open(p1, 'r', encoding='utf-8') as f:
                    content = f.read()
                    x = content
                res = re.search('```python(.*?)```', content, re.S)
                if not res:
                    continue
                print(p1)
                res = res.group(1)
                p2 = root + "/tmp.py"
                with open(p2, 'w', encoding='utf-8') as f:
                    f.write(res)
                os.system(f'black -S "{p2}"')
                with open(p2, 'r', encoding='utf-8') as f:
                    content = f.read()
                content = x.replace(res, '\n' + content)
                with open(p1, 'w', encoding='utf-8') as f:
                    f.write(content)
                os.remove(p2)


def git_add():
    command = 'git add .'
    os.system(command)


if __name__ == '__main__':
    format_cpp()
    format()
    format_py()
    git_add()
