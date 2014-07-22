#!/bin/bash
cd src/main/ruby/ && \
 GEM_PATH=`pwd`/lib/jruby/1.8 java \
  -jar ~/.m2/repository/org/jruby/jruby-complete/1.7.13/jruby-complete-1.7.13.jar \
  -S lib/jruby/1.8/bin/bundle install --path lib/
