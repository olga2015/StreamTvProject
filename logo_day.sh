#!/bin/bash
if [ 'date +%u' -gt 5]
then
cp ./weekend/logo.jpeg ./target
else ./businessday/logo.jpeg ./target
fi