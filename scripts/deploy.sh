#!/bin/bash
base64 --decode --ignore-garbage <<< $DEPLOY_MACHINE_PUBKEY > ~/.ssh/known_hosts
base64 --decode --ignore-garbage <<< $DEPLOY_MACHINE_KEY > ~/.ssh/id_rsa
chmod 400 ~/.ssh/id_rsa

scp -o "BatchMode yes" -P $DEPLOY_MACHINE_PORT -r $TRAVIS_BUILD_DIR/build/libs/redact.jar $DEPLOY_MACHINE_USER@$DEPLOY_MACHINE_HOST:$DEPLOY_DIR
ssh -o "BatchMode yes" -tt -p $DEPLOY_MACHINE_PORT $DEPLOY_MACHINE_USER@$DEPLOY_MACHINE_HOST << EOF
supervisorctl restart redactBackend
exit
EOF
