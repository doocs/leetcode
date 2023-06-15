import os.path
import re

path = os.getcwd()


def prettier_format():
    """Format code with prettier"""
    # before prettier, add `<?php` to php files
    for root, _, files in os.walk(path):
        for name in files:
            if name.endswith('.php'):
                p = root + '/' + name
                with open(p, 'r', encoding='utf-8') as f:
                    content = f.read()
                    content = '<?php\n' + content
                with open(p, 'w', encoding='utf-8') as f:
                    f.write(content)

    # start formatting with prettier
    for suffix in ['md', 'js', 'ts', 'php']:
        command = f'npx prettier --write "**/*.{suffix}"'
        os.system(command)

    # after prettier, remove `<?php` from php files
    for root, _, files in os.walk(path):
        for name in files:
            if name.endswith('.php'):
                p = root + '/' + name
                with open(p, 'r', encoding='utf-8') as f:
                    content = f.read()
                    content = content.replace('<?php\n', '')
                with open(p, 'w', encoding='utf-8') as f:
                    f.write(content)


def clang_format():
    """Format code with clang-format"""
    suffix = ['c', 'cpp', 'java']
    for root, _, files in os.walk(path):
        for name in files:
            for suf in suffix:
                if name.endswith('.' + suf):
                    local_path = root + '/' + name
                    command = f'clang-format -i --style=file "{local_path}"'
                    print(command)
                    os.system(command)

    for root, _, files in os.walk(path):
        for name in files:
            if name.endswith('.md'):
                p = root + '/' + name
                with open(p, 'r', encoding='utf-8') as f:
                    content = f.read()
                    x = content
                print(p)
                for suf in suffix:
                    res = re.findall(f'```{suf}\n(.*?)```', content, re.S)
                    if not res:
                        continue
                    for v in res:
                        p2 = f'{root}/Solution2.{suf}'
                        with open(p2, 'w', encoding='utf-8') as f:
                            f.write(v)
                        command = f'clang-format -i --style=file "{p2}"'
                        os.system(command)
                        with open(p2, 'r', encoding='utf-8') as f:
                            content = f.read()
                        content = x.replace(v, content)
                        with open(p, 'w', encoding='utf-8') as f:
                            f.write(content)
                        os.remove(p2)


def black_format():
    """Format python code with black"""
    command = 'black -S .'
    os.system(command)

    for root, _, files in os.walk(path):
        for name in files:
            if name.endswith('.md'):
                p1 = root + '/' + name
                with open(p1, 'r', encoding='utf-8') as f:
                    content = f.read()
                    x = content
                res = re.findall('```python\n(.*?)```', content, re.S)
                if not res:
                    continue
                print(p1)
                for v in res:
                    p2 = root + "/tmp.py"
                    with open(p2, 'w', encoding='utf-8') as f:
                        f.write(v)
                    os.system(f'black -S "{p2}"')
                    with open(p2, 'r', encoding='utf-8') as f:
                        content = f.read()
                    content = x.replace(v, content)
                    with open(p1, 'w', encoding='utf-8') as f:
                        f.write(content)
                    os.remove(p2)


def golang_format():
    """Format golang code with gofmt"""
    for root, _, files in os.walk(path):
        for name in files:
            if name.endswith('.go'):
                p = root + '/' + name
                with open(p, 'r', encoding='utf-8') as f:
                    content = f.read()
                if content.startswith('package '):
                    continue
                content = 'package main\n' + content
                with open(p, 'w', encoding='utf-8') as f:
                    f.write(content)

    command = 'gofmt -w .'
    os.system(command)

    for root, _, files in os.walk(path):
        for name in files:
            if name.endswith('.go'):
                p = root + '/' + name
                with open(p, 'r', encoding='utf-8') as f:
                    content = f.read()
                    content = content.rstrip()
                    if 'sorting' not in p:
                        content = content.replace('package main\n\n', '').replace(
                            'package main\n', ''
                        )
                with open(p, 'w', encoding='utf-8') as f:
                    f.write(content)


def git_add():
    """Git add all files"""
    command = 'git add .'
    os.system(command)


if __name__ == '__main__':
    # prettier_format()
    # black_format()
    clang_format()
    # golang_format()
    git_add()
