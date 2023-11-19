dir=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)

original_pwd=$(pwd)

# cd into out
cd "$dir/out"

java Parser $original_pwd/$1