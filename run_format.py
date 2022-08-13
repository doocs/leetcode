import os.path

path = os.getcwd()
for root, dirs, files in os.walk(path):
    for name in files:
        if name.endswith('.cpp') or name.endswith('.c'):
            local_path = root + '/' + name
            command = f'clang-format -i --style=file "{local_path}"'
            print(command)
            os.system(command)
