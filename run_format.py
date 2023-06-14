import os.path
import re

path = os.getcwd()


def prettier_format():
    """Format code with prettier"""
    for suffix in ['md', 'js', 'ts']:
        command = f'npx prettier --write "**/*.{suffix}"'
        os.system(command)

    # format php code
    for root, _, files in os.walk(path):
        for name in files:
            if name.endswith('.php'):
                p1 = root + '/' + name
                with open(p1, 'r', encoding='utf-8') as f:
                    content = f.read()
                    content = '<?php\n' + content
                with open(p1, 'w', encoding='utf-8') as f:
                    f.write(content)
    command = 'npx prettier --write "**/*.php"'
    os.system(command)
    for root, _, files in os.walk(path):
        for name in files:
            if name.endswith('.php'):
                p1 = root + '/' + name
                with open(p1, 'r', encoding='utf-8') as f:
                    content = f.read()
                    content = content.replace('<?php\n', '')
                with open(p1, 'w', encoding='utf-8') as f:
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
                p1 = root + '/' + name
                with open(p1, 'r', encoding='utf-8') as f:
                    content = f.read()
                    x = content
                print(p1)
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
                        with open(p1, 'w', encoding='utf-8') as f:
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


def git_add():
    """Git add all files"""
    command = 'git add .'
    os.system(command)


if __name__ == '__main__':
    prettier_format()
    black_format()
    # clang_format()
    git_add()
