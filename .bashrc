# make mctl callable
alias mctl=~/mctl/mctl.sh

# automically export display variable to ssh session
if [ -n "$SSH_CONNECTION" ]; then
    export DISPLAY=:0
fi
