# automically export display variable to ssh session
if [ -n "$SSH_CONNECTION" ]; then
    export DISPLAY=:0
fi
