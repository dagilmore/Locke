require 'sinatra/base'
require 'sinatra/rabbit'
 
class MyApp < Sinatra::Base
  include Sinatra::Rabbit
 
  get '/' do
    redirect '/images'
  end
 
  collection :images do
    description "Collection of virtual machine images"
 
    operation :index do
      description "List of images"
      control do
        @images = Image.all
        haml :'images/index'
      end
    end
 
    operation :create do
      description "Create new image"
      params :name, :string, :required, "Name of the image"
      params :owner, :string, :optional, "Owner of the image"
      control do
        @images = Image.create(:name => params[:name], :owner => params[:owner])
        redirect "/images/#{@image.name}"
      end
    end
    
    operation :show do
      description "Display details of image"
      params :id, :string, :required, "Image ID"
      control do
        @image = Image.get(params[:id])
        haml :'images/show'
      end
    end
 
    operation :destroy do
      description "Delete image"
      params :id, :string, :required, "Image ID"
      control do
        Image.destroy!(params[:id])
        halt [410, "Image deleted"]
      end
    end
 
  end
end