#
# ~/.bashrc
#

# If not running interactively, don't do anything
[[ $- != *i* ]] && return

alias ls='ls --color=auto'
alias grep='grep --color=auto'
PS1='[\u@\h \W]\$ '

# make mctl callable
alias mctl=/home/gui/mctl/mctl.sh

# automically export display variable to ssh session
if [ -n "$SSH_CONNECTION" ]; then
    export DISPLAY=:0
fi
