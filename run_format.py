from typing import List

import os.path
import re
import black

suffixes = ['md', 'py', 'java', 'c', 'cpp', 'go', 'php', 'cs', 'rs', 'js', 'ts']

code_blocks = ['python', 'java', 'cpp', 'c', 'go', 'ts', 'js', 'php', 'cs', 'rs']


def add_header(path: str):
    """Add header to php and go files"""
    print(f'[add header] path: {path}')
    with open(path, 'r', encoding='utf-8') as f:
        content = f.read()
    if path.endswith('.php'):
        content = '<?php\n' + content
    elif path.endswith('.go') and 'sorting' not in path:
        content = 'package main\n' + content
    else:
        return
    with open(path, 'w', encoding='utf-8') as f:
        f.write(content)


def remove_header(path: str):
    """Remove header from php and go files"""
    print(f'[remove header] path: {path}')
    with open(path, 'r', encoding='utf-8') as f:
        content = f.read()
    if path.endswith('.php'):
        content = content.rstrip()
        content = content.replace('<?php\n', '')
    elif path.endswith('.go') and 'sorting' not in path:
        content = content.replace('package main\n\n', '').replace('package main\n', '')
    else:
        return
    with open(path, 'w', encoding='utf-8') as f:
        f.write(content)


def find_all_paths() -> List[str]:
    """Find all paths of files with suffixes"""
    paths = []
    for root, _, files in os.walk(os.getcwd()):
        for file in files:
            path = root + '/' + file
            if 'node_modules' in path or '__pycache__' in path or '.git' in path:
                continue
            if any(path.endswith(f'.{suf}') for suf in suffixes):
                paths.append(path)
    return paths


def format_inline_code(path: str):
    """Format inline code in .md file"""
    if not path.endswith('.md'):
        return
    with open(path, 'r', encoding='utf-8') as f:
        content = f.read()
    root = path[: path.rfind('/')]
    print(f'[format inline code] path: {path}')
    for suf in code_blocks:
        res = re.findall(f'```{suf}\n(.*?)```', content, re.S)
        for block in res or []:
            # skip empty code block
            if not block or not block.strip():
                continue
            if suf in ['c', 'cpp', 'java', 'go']:
                file = f'{root}/Solution2.{suf}'
                with open(file, 'w', encoding='utf-8') as f:
                    f.write(block)
                if suf == 'go':
                    add_header(file)
                    os.system(f'gofmt -w "{file}"')
                    remove_header(file)
                else:
                    os.system(f'npx clang-format -i --style=file "{file}"')
                with open(file, 'r', encoding='utf-8') as f:
                    new_block = f.read()
                content = content.replace(block, new_block)
                os.remove(file)
            elif suf == 'python':
                new_block = black.format_str(
                    block, mode=black.FileMode(string_normalization=False)
                )
                content = content.replace(block, new_block)

    with open(path, 'w', encoding='utf-8') as f:
        f.write(content)


def run():
    """Start formatting"""
    paths = find_all_paths()

    # for path in paths:
    #     add_header(path)
    #     if any(path.endswith(suf) for suf in ['c', 'cpp', 'java']):
    #         # format with clang-format
    #         os.system(f'npx clang-format -i --style=file "{path}"')

    # # format with prettier
    # os.system('npx prettier --write "**/*.{md,js,ts,php}"')

    # # format with gofmt
    # os.system('gofmt -w .')

    # for path in paths:
    #     remove_header(path)
    for path in paths:
        format_inline_code(path)


if __name__ == '__main__':
    run()
