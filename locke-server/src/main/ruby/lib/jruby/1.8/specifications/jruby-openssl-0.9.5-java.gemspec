# -*- encoding: utf-8 -*-
# stub: jruby-openssl 0.9.5 java lib

Gem::Specification.new do |s|
  s.name = "jruby-openssl"
  s.version = "0.9.5"
  s.platform = "java"

  s.required_rubygems_version = Gem::Requirement.new(">= 0") if s.respond_to? :required_rubygems_version=
  s.authors = ["Ola Bini", "JRuby contributors"]
  s.date = "2014-06-24"
  s.description = "JRuby-OpenSSL is an add-on gem for JRuby that emulates the Ruby OpenSSL native library."
  s.email = "ola.bini@gmail.com"
  s.homepage = "https://github.com/jruby/jruby"
  s.require_paths = ["lib"]
  s.requirements = ["jar org.bouncycastle:bcpkix-jdk15on, 1.47", "jar org.bouncycastle:bcprov-jdk15on, 1.47"]
  s.rubyforge_project = "jruby/jruby"
  s.rubygems_version = "2.1.11"
  s.summary = "JRuby OpenSSL"
end
