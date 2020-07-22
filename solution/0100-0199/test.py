import os

no = '0100-0199'

for path in os.listdir('.'):

    if os.path.isdir(f'./{path}'):
        try:
            with open(f'./{path}/README.md', 'r+', encoding='utf-8') as f:
                text = f.read()
                new_path = path.replace(' ', "%20")
                text = text.replace('## 题目描述', f'[English Version](/solution/{no}/{new_path}/README_EN.md)\n\n## 题目描述')
                f.seek(0)
                f.truncate()
                f.write(text)
        except Exception as e:
            pass

        try:

            with open(f'./{path}/README_EN.md', 'r+', encoding='utf-8') as f:
                text = f.read()
                new_path = path.replace(' ', "%20")
                text = text.replace('## Description', f'[中文文档](/solution/{no}/{new_path}/README.md)\n\n## Description')
                f.seek(0)
                f.truncate()
                f.write(text)
        except Exception as e:
            pass